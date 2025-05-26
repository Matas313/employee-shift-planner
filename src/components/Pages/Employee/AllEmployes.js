import React, { useEffect, useState } from 'react';
import './AllEmployes.css';

function AllEmployees() {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:8080/admins/allemployees')
      .then(response => {
        if (!response.ok) {
          throw new Error('Serverio klaida: ' + response.status);
        }
        return response.json();
      })
      .then(data => {
        console.log('Gauti darbuotojai:', data);
        setEmployees(data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Klaida:', err);
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) return <div>Kraunama...</div>;
  if (error) return <div>Klaida: {error}</div>;

  return (
    <div className="employees-container">
      {employees.map(emp => (
        <div className="employee-card" key={emp.id}>
          <div className="employee-name">{emp.name}</div>
          <div className="employee-position">{emp.position}</div>
          <div className="employee-contact">
            <div>Email: {emp.email}</div>
            <div>Tel: {emp.phone}</div>
          </div>
        </div>
      ))}
    </div>
  );
}

export default AllEmployees;
