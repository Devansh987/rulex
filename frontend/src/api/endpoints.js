export const AUTH = {
  SIGNUP: '/public/signup',
  LOGIN: '/public/login',
};

export const USER = {
  UPDATE: '/user',
  DELETE: '/user',
};

export const ADMIN = {
  GET_ALL_USERS: '/admin',
  GET_USER: (userName) => `/admin/${userName}`,
};

export const TENANT = {
  CREATE: '/admin/tenant',
  GET: (code) => `/admin/tenant/${code}`,
  UPDATE: (code) => `/admin/tenant/${code}`,
  DELETE: (code) => `/admin/tenant/${code}`,
};

export const POLICY = {
  CREATE: '/admin/policy',
  GET_ALL: (tenantCode) => `/admin/policy/${tenantCode}`,
  GET: (tenantCode, policyCode) => `/admin/policy/tenant/${tenantCode}/${policyCode}`,
  UPDATE: (tenantCode, policyCode) => `/admin/policy/tenant/${tenantCode}/${policyCode}`,
  DELETE: (tenantCode, policyCode) => `/admin/policy/tenant/${tenantCode}/${policyCode}`,
};

export const RULES = {
  CREATE: (tenantCode, policyCode) => `/admin/rules/tenant/${tenantCode}/policy/${policyCode}`,
  GET_ALL: (tenantCode, policyCode) => `/admin/rules/tenant/${tenantCode}/policy/${policyCode}`,
  UPDATE: (ruleId) => `/admin/rules/${ruleId}`,
  DELETE: (ruleId) => `/admin/rules/${ruleId}`,
};

export const EVALUATE = {
  RUN: '/client/api/evaluate',
};
