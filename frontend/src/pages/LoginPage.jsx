import { LockKeyhole, ShieldCheck } from 'lucide-react';
import { useState } from 'react';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import Alert from '../components/Alert';
import LoadingSpinner from '../components/LoadingSpinner';
import { useAuth } from '../hooks/useAuth';
import { getApiError } from '../utils/errors';

function LoginPage() {
  const navigate = useNavigate();
  const { isAuthenticated, login } = useAuth();
  const [form, setForm] = useState({ userName: '', password: '' });
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  if (isAuthenticated) {
    return <Navigate to="/dashboard" replace />;
  }

  const updateField = (event) => {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setLoading(true);

    try {
      await login(form.userName.trim(), form.password);
      navigate('/dashboard', { replace: true });
    } catch (err) {
      setError(getApiError(err, 'Unable to sign in. Check your credentials and try again.'));
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="login-page">
      <section className="login-panel">
        <div className="login-copy">
          <span className="brand-mark large">R</span>
          <h1>RuleX Console</h1>
          <p>Sign in to create business rules, review policies, and evaluate facts against your Spring Boot rule engine.</p>
          <div className="login-feature">
            <ShieldCheck size={18} aria-hidden="true" />
            <span>JWT protected admin and evaluation workflows</span>
          </div>
        </div>

        <form className="login-form" onSubmit={handleSubmit}>
          <div>
            <span className="eyebrow">Authentication</span>
            <h2>Welcome back</h2>
          </div>

          {error && <Alert>{error}</Alert>}

          <label>
            Username
            <input
              autoComplete="username"
              name="userName"
              onChange={updateField}
              placeholder="admin"
              required
              type="text"
              value={form.userName}
            />
          </label>

          <label>
            Password
            <input
              autoComplete="current-password"
              name="password"
              onChange={updateField}
              placeholder="Enter password"
              required
              type="password"
              value={form.password}
            />
          </label>

          <button className="primary-button" disabled={loading} type="submit">
            {loading ? <LoadingSpinner label="Signing in" /> : <><LockKeyhole size={18} aria-hidden="true" /> Sign in</>}
          </button>

          <p className="form-switch">
            New to RuleX? <Link to="/signup">Create an account</Link>
          </p>
        </form>
      </section>
    </main>
  );
}

export default LoginPage;
