import { useCallback, useMemo, useState } from 'react';
import api from '../api/axios';
import { AUTH } from '../api/endpoints';
import AuthContext from './AuthContextCore';

function decodeToken(token) {
  try {
    const payload = token.split('.')[1] || '';
    const normalized = payload.replace(/-/g, '+').replace(/_/g, '/');
    const padded = normalized.padEnd(normalized.length + ((4 - (normalized.length % 4)) % 4), '=');
    return JSON.parse(atob(padded));
  } catch {
    return null;
  }
}

function isTokenExpired(decoded) {
  if (!decoded || !decoded.exp) return true;
  return decoded.exp * 1000 < Date.now();
}

function extractUser(decoded) {
  if (!decoded) return null;

  const rawRole = decoded.role || decoded.authority || decoded.roles?.[0] || 'USER';

  return {
    userName: decoded.sub || decoded.userName || decoded.username || 'user',
    role: String(rawRole).replace('ROLE_', ''),
  };
}

export function AuthProvider({ children }) {
  const [session, setSession] = useState(() => {
    const storedToken = localStorage.getItem('rulex_token');

    if (storedToken) {
      const decoded = decodeToken(storedToken);

      if (decoded && !isTokenExpired(decoded)) {
        return {
          token: storedToken,
          user: extractUser(decoded),
        };
      }

      localStorage.removeItem('rulex_token');
      localStorage.removeItem('rulex_user');
    }

    return {
      token: null,
      user: null,
    };
  });

  const login = useCallback(async (userName, password) => {
    const response = await api.post(AUTH.LOGIN, { userName, password });
    const jwt = typeof response.data === 'string' ? response.data : response.data.token;

    if (!jwt) {
      throw new Error('Login succeeded but no token was returned.');
    }

    const decoded = decodeToken(jwt);
    if (!decoded) {
      throw new Error('Invalid token received from server.');
    }

    const userData = extractUser(decoded);

    localStorage.setItem('rulex_token', jwt);
    localStorage.setItem('rulex_user', JSON.stringify(userData));

    setSession({ token: jwt, user: userData });

    return userData;
  }, []);

  const signup = useCallback(async (userData) => {
    const response = await api.post(AUTH.SIGNUP, userData);
    return response.data;
  }, []);

  const logout = useCallback(() => {
    localStorage.removeItem('rulex_token');
    localStorage.removeItem('rulex_user');
    setSession({ token: null, user: null });
  }, []);

  const value = useMemo(() => ({
    user: session.user,
    token: session.token,
    loading: false,
    login,
    signup,
    logout,
    isAuthenticated: !!session.token && !!session.user,
  }), [login, logout, session.token, session.user, signup]);

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
}
