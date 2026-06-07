import { Play } from 'lucide-react';
import { useState } from 'react';
import api from '../api/axios';
import { EVALUATE } from '../api/endpoints';
import Alert from '../components/Alert';
import LoadingSpinner from '../components/LoadingSpinner';
import PageHeader from '../components/PageHeader';
import { getApiError } from '../utils/errors';

const sampleFacts = '{\n  "age": 25,\n  "country": "IN"\n}';

function EvaluationPage() {
  const [form, setForm] = useState({ tenantCode: '', policyCode: '', facts: sampleFacts });
  const [result, setResult] = useState(null);
  const [error, setError] = useState('');
  const [loading, setLoading] = useState(false);

  const updateField = (event) => {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setResult(null);
    setLoading(true);

    let facts;
    try {
      facts = JSON.parse(form.facts);
    } catch {
      setError('Facts must be valid JSON.');
      setLoading(false);
      return;
    }

    try {
      const response = await api.post(EVALUATE.RUN, {
        tenantCode: form.tenantCode.trim(),
        policyCode: form.policyCode.trim(),
        facts,
      });
      setResult(response.data);
    } catch (err) {
      setError(getApiError(err, 'Unable to evaluate policy.'));
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <PageHeader eyebrow="Evaluation" title="Policy Evaluation" description="Send tenant, policy, and facts to the rule engine." />

      <div className="evaluation-grid">
        <form className="form-surface" onSubmit={handleSubmit}>
          {error && <Alert>{error}</Alert>}

          <div className="form-grid two">
            <label>
              Tenant code
              <input name="tenantCode" onChange={updateField} placeholder="TENANT_A" required value={form.tenantCode} />
            </label>
            <label>
              Policy code
              <input name="policyCode" onChange={updateField} placeholder="POLICY_001" required value={form.policyCode} />
            </label>
          </div>

          <label>
            Facts JSON
            <textarea name="facts" onChange={updateField} required rows={12} value={form.facts} />
          </label>

          <button className="primary-button" disabled={loading} type="submit">
            {loading ? <LoadingSpinner label="Evaluating" /> : <><Play size={18} aria-hidden="true" /> Run evaluation</>}
          </button>
        </form>

        <section className="result-panel">
          <span className="eyebrow">Decision</span>
          {result ? (
            <div className="decision-card">
              <strong>{result.decision || 'NO_DECISION'}</strong>
              <p>{result.reason || result.Reason || 'No reason returned.'}</p>
              <code>{result.matchedRules || 'No rule matched'}</code>
            </div>
          ) : (
            <div className="empty-state">
              <h2>No evaluation yet</h2>
              <p>The decision response will appear here after a successful run.</p>
            </div>
          )}
        </section>
      </div>
    </>
  );
}

export default EvaluationPage;
