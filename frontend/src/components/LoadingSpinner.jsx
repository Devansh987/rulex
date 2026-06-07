function LoadingSpinner({ fullPage = false, label = 'Loading' }) {
  return (
    <div className={fullPage ? 'spinner-page' : 'spinner-row'}>
      <span className="spinner" aria-hidden="true" />
      <span>{label}</span>
    </div>
  );
}

export default LoadingSpinner;
