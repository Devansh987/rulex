# RuleX – Rule-Driven Policy Enforcement Engine (Spring Boot)

RuleX is a multi-tenant, rule-driven policy enforcement engine built using Spring Boot.
It allows organizations (tenants) to configure their own policies and rules via Admin APIs,
and then evaluate decisions at runtime using a single generic evaluation API.

This project is designed as a centralized decision-making microservice that can be used
by multiple applications for approvals, validations, access control, pricing decisions, etc.

---

## 🚀 Key Features

### ✅ Multi-Tenant Support
- Each tenant has isolated policies and rules.
- Two tenants can have the same policyCode but different rule logic.

### ✅ Admin Configuration APIs
Admins can:
- Create/Update/Disable tenants
- Create/Update/Disable policies
- Create/Update/Disable rules

### ✅ Runtime Policy Evaluation API
Clients can evaluate a policy by sending:
- tenantCode
- policyCode
- runtime facts (dynamic Map)

The rule engine applies rules in priority order and returns a decision.

### ✅ Rule Engine (Core Logic)
- Rules are evaluated in ascending priority.
- First matching rule wins.
- If no rule matches, default response is returned.

### ✅ Role-Based Security (Basic)
- `/admin/**` endpoints require ADMIN role
- `/api/evaluate` requires CLIENT role
- Authentication uses HTTP Basic (in-memory users)

---

## 🏗️ Project Structure

