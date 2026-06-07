import { UserPlus } from 'lucide-react';
import { useState } from 'react';
import { Link, Navigate, useNavigate } from 'react-router-dom';
import Alert from '../components/Alert';
import LoadingSpinner from '../components/LoadingSpinner';
import { useAuth } from '../hooks/useAuth';
import { getApiError } from '../utils/errors';

const initialForm = {
  userName: '',
  email: '',
  password: '',
  role: 'CLIENT',
};

function SignupPage() {
  const navigate = useNavigate();
  const { isAuthenticated, signup } = useAuth();
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
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
    setSuccess('');
    setLoading(true);

    try {
      await signup({
        userName: form.userName.trim(),
        email: form.email.trim(),
        password: form.password,
        role: form.role,
      });
      setSuccess('Account created. You can sign in now.');
      setForm(initialForm);
      setTimeout(() => navigate('/login'), 700);
    } catch (err) {
      setError(getApiError(err, 'Unable to create account.'));
    } finally {
      setLoading(false);
    }
  };

  return (
    <main className="login-page">
      <section className="login-panel">
        <div className="login-copy">
          <span className="brand-mark large">R</span>
          <h1>Create Account</h1>
          <p>Register a user for the RuleX console with the role your rule workflow needs.</p>
        </div>

        <form className="login-form" onSubmit={handleSubmit}>
          <div>
            <span className="eyebrow">Sign up</span>
            <h2>New account</h2>
          </div>

          {error && <Alert>{error}</Alert>}
          {success && <Alert type="success">{success}</Alert>}

          <label>
            Username
            <input
              autoComplete="username"
              maxLength={20}
              minLength={3}
              name="userName"
              onChange={updateField}
              placeholder="Devansh"
              required
              type="text"
              value={form.userName}
            />
          </label>

          <label>
            Email
            <input
              autoComplete="email"
              name="email"
              onChange={updateField}
              placeholder="you@example.com"
              required
              type="email"
              value={form.email}
            />
          </label>

          <label>
            Password
            <input
              autoComplete="new-password"
              minLength={6}
              name="password"
              onChange={updateField}
              placeholder="At least 6 characters"
              required
              type="password"
              value={form.password}
            />
          </label>

          <label>
            Role
            <select name="role" onChange={updateField} value={form.role}>
              <option value="CLIENT">CLIENT</option>
              <option value="ADMIN">ADMIN</option>
            </select>
          </label>

          <button className="primary-button" disabled={loading} type="submit">
            {loading ? <LoadingSpinner label="Creating" /> : <><UserPlus size={18} aria-hidden="true" /> Create account</>}
          </button>

          <p className="form-switch">
            Already have an account? <Link to="/login">Sign in</Link>
          </p>
        </form>
      </section>
    </main>
  );
}

export default SignupPage;
