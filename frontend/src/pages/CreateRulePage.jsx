import { Save } from 'lucide-react';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import api from '../api/axios';
import { RULES } from '../api/endpoints';
import Alert from '../components/Alert';
import LoadingSpinner from '../components/LoadingSpinner';
import PageHeader from '../components/PageHeader';
import { getApiError } from '../utils/errors';

const initialForm = {
  tenantCode: '',
  policyCode: '',
  field: '',
  operator: '==',
  value: '',
  decision: '',
  priority: 1,
};

function CreateRulePage() {
  const navigate = useNavigate();
  const [form, setForm] = useState(initialForm);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const [loading, setLoading] = useState(false);

  const updateField = (event) => {
    const { name, value } = event.target;
    setForm((current) => ({ ...current, [name]: value }));
  };

  const handleSubmit = async (event) => {
    event.preventDefault();
    setError('');
    setSuccess('');
    setLoading(true);

    const payload = {
      field: form.field.trim(),
      operator: form.operator,
      value: form.value.trim(),
      decision: form.decision.trim(),
      priority: Number(form.priority),
      active: true,
    };

    try {
      await api.post(RULES.CREATE(form.tenantCode.trim(), form.policyCode.trim()), payload);
      setSuccess('Rule created successfully.');
      setForm(initialForm);
      setTimeout(() => navigate('/rules'), 500);
    } catch (err) {
      setError(getApiError(err, 'Unable to create rule.'));
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <PageHeader eyebrow="Rules" title="Create Rule" description="Attach a new active rule to a tenant policy." />

      <form className="form-surface" onSubmit={handleSubmit}>
        {error && <Alert>{error}</Alert>}
        {success && <Alert type="success">{success}</Alert>}

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

        <div className="form-grid three">
          <label>
            Field
            <input name="field" onChange={updateField} placeholder="age" required value={form.field} />
          </label>
          <label>
            Operator
            <select name="operator" onChange={updateField} value={form.operator}>
              <option value="==">==</option>
              <option value="!=">!=</option>
              <option value=">">&gt;</option>
              <option value=">=">&gt;=</option>
              <option value="<">&lt;</option>
              <option value="<=">&lt;=</option>
            </select>
          </label>
          <label>
            Value
            <input name="value" onChange={updateField} placeholder="18" required value={form.value} />
          </label>
        </div>

        <div className="form-grid two">
          <label>
            Decision
            <input name="decision" onChange={updateField} placeholder="APPROVED" required value={form.decision} />
          </label>
          <label>
            Priority
            <input min="0" name="priority" onChange={updateField} required type="number" value={form.priority} />
          </label>
        </div>

        <button className="primary-button" disabled={loading} type="submit">
          {loading ? <LoadingSpinner label="Creating" /> : <><Save size={18} aria-hidden="true" /> Save rule</>}
        </button>
      </form>
    </>
  );
}

export default CreateRulePage;
