import EmployeeForm from './EmployeeForm';
import DeleteEmployee from './DeleteEmployee';
import AllEmployes from './AllEmployes'

function EmployeePage() {
  return (
    <div>
      <h2>Darbuotojai</h2>
      <EmployeeForm />
      <DeleteEmployee />
      <AllEmployes />
    </div>
  );
}

export default EmployeePage;
