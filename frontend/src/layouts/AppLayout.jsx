import { ClipboardCheck, Gauge, LogOut, PlusCircle, Scale } from 'lucide-react';
import { NavLink, Outlet } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';

const navItems = [
  { to: '/dashboard', label: 'Dashboard', icon: Gauge },
  { to: '/rules/new', label: 'Create Rule', icon: PlusCircle },
  { to: '/rules', label: 'Rules', icon: ClipboardCheck },
  { to: '/evaluate', label: 'Evaluate', icon: Scale },
];

function AppLayout() {
  const { logout, user } = useAuth();

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand">
          <span className="brand-mark">R</span>
          <div>
            <strong>RuleX</strong>
            <small>Rule Engine</small>
          </div>
        </div>

        <nav className="nav-list" aria-label="Main navigation">
          {navItems.map(({ to, label, icon: Icon }) => (
            <NavLink key={to} to={to} className={({ isActive }) => `nav-item ${isActive ? 'active' : ''}`}>
              <Icon size={18} aria-hidden="true" />
              <span>{label}</span>
            </NavLink>
          ))}
        </nav>

        <div className="sidebar-footer">
          <div className="user-chip">
            <span>{user?.userName?.slice(0, 1)?.toUpperCase() || 'U'}</span>
            <div>
              <strong>{user?.userName || 'User'}</strong>
              <small>{user?.role || 'Authenticated'}</small>
            </div>
          </div>
          <button className="icon-text-button ghost" type="button" onClick={logout}>
            <LogOut size={18} aria-hidden="true" />
            <span>Logout</span>
          </button>
        </div>
      </aside>

      <main className="content">
        <Outlet />
      </main>
    </div>
  );
}

export default AppLayout;
