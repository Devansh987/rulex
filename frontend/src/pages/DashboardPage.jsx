import { ClipboardCheck, PlugZap, Scale, ShieldCheck } from 'lucide-react';
import { Link } from 'react-router-dom';
import PageHeader from '../components/PageHeader';
import { useAuth } from '../hooks/useAuth';

const stats = [
  { label: 'Auth', value: 'JWT', icon: ShieldCheck },
  { label: 'Rules API', value: '/admin/rules', icon: ClipboardCheck },
  { label: 'Evaluation API', value: '/client/api/evaluate', icon: Scale },
  { label: 'Backend', value: 'Render', icon: PlugZap },
];

function DashboardPage() {
  const { user } = useAuth();

  return (
    <>
      <PageHeader
        eyebrow="Dashboard"
        title={`Welcome, ${user?.userName || 'user'}`}
        description="Manage policy rules and test decisions from a focused console."
        actions={<Link className="primary-button" to="/rules/new">Create rule</Link>}
      />

      <section className="stats-grid">
        {stats.map(({ label, value, icon: Icon }) => (
          <article className="stat-card" key={label}>
            <Icon size={22} aria-hidden="true" />
            <span>{label}</span>
            <strong>{value}</strong>
          </article>
        ))}
      </section>

      <section className="workflow-grid">
        <Link className="workflow-card" to="/rules/new">
          <h2>Create Rule</h2>
          <p>Add a field, operator, comparison value, decision, and priority to an existing tenant policy.</p>
        </Link>
        <Link className="workflow-card" to="/rules">
          <h2>List Rules</h2>
          <p>Fetch active rules for a tenant and policy, then review rule priority and decisions.</p>
        </Link>
        <Link className="workflow-card" to="/evaluate">
          <h2>Policy Evaluation</h2>
          <p>Submit JSON facts and inspect the returned decision, reason, and matched rule.</p>
        </Link>
      </section>
    </>
  );
}

export default DashboardPage;
