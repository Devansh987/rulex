import { RefreshCw, Trash2 } from 'lucide-react';
import { useState } from 'react';
import api from '../api/axios';
import { RULES } from '../api/endpoints';
import Alert from '../components/Alert';
import LoadingSpinner from '../components/LoadingSpinner';
import PageHeader from '../components/PageHeader';
import { getApiError } from '../utils/errors';

function RulesPage() {
  const [filters, setFilters] = useState({ tenantCode: '', policyCode: '' });
  const [rules, setRules] = useState([]);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);
  const [deletingId, setDeletingId] = useState(null);

  const updateField = (event) => {
    const { name, value } = event.target;
    setFilters((current) => ({ ...current, [name]: value }));
  };

  const fetchRules = async (event) => {
    event?.preventDefault();
    setError('');
    setLoading(true);

    try {
      const response = await api.get(RULES.GET_ALL(filters.tenantCode.trim(), filters.policyCode.trim()));
      setRules(Array.isArray(response.data) ? response.data : []);
    } catch (err) {
      setError(getApiError(err, 'Unable to fetch rules.'));
      setRules([]);
    } finally {
      setLoading(false);
    }
  };

  const disableRule = async (ruleId) => {
    setError('');
    setDeletingId(ruleId);

    try {
      await api.delete(RULES.DELETE(ruleId));
      setRules((current) => current.filter((rule) => rule.id !== ruleId));
    } catch (err) {
      setError(getApiError(err, 'Unable to disable rule.'));
    } finally {
      setDeletingId(null);
    }
  };

  return (
    <>
      <PageHeader eyebrow="Rules" title="List Rules" description="Load active rules for a tenant and policy." />

      <form className="toolbar-form" onSubmit={fetchRules}>
        <label>
          Tenant code
          <input name="tenantCode" onChange={updateField} placeholder="TENANT_A" required value={filters.tenantCode} />
        </label>
        <label>
          Policy code
          <input name="policyCode" onChange={updateField} placeholder="POLICY_001" required value={filters.policyCode} />
        </label>
        <button className="secondary-button" disabled={loading} type="submit">
          {loading ? <LoadingSpinner label="Loading" /> : <><RefreshCw size={18} aria-hidden="true" /> Load</>}
        </button>
      </form>

      {error && <Alert>{error}</Alert>}

      <section className="table-surface">
        {loading ? (
          <LoadingSpinner label="Fetching rules" />
        ) : rules.length === 0 ? (
          <div className="empty-state">
            <h2>No rules loaded</h2>
            <p>Enter a tenant code and policy code to view active rules.</p>
          </div>
        ) : (
          <div className="table-scroll">
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Condition</th>
                  <th>Decision</th>
                  <th>Priority</th>
                  <th>Status</th>
                  <th>Created</th>
                  <th aria-label="Actions" />
                </tr>
              </thead>
              <tbody>
                {rules.map((rule) => (
                  <tr key={rule.id}>
                    <td>{rule.id}</td>
                    <td><code>{rule.field} {rule.operator} {rule.value}</code></td>
                    <td>{rule.decision}</td>
                    <td>{rule.priority}</td>
                    <td><span className={rule.active ? 'status active' : 'status'}>{rule.active ? 'Active' : 'Disabled'}</span></td>
                    <td>{rule.createdAt ? new Date(rule.createdAt).toLocaleString() : '-'}</td>
                    <td>
                      <button
                        aria-label={`Disable rule ${rule.id}`}
                        className="icon-button danger"
                        disabled={deletingId === rule.id}
                        onClick={() => disableRule(rule.id)}
                        type="button"
                      >
                        <Trash2 size={17} aria-hidden="true" />
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </section>
    </>
  );
}

export default RulesPage;
