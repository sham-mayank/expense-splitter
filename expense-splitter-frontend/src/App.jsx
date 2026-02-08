import CreateExpense from "./components/CreateExpense";
import Balances from "./components/Balances";

export default function App() {
  return (
    <div style={{ padding: "30px", fontFamily: "Arial" }}>
      <h1>Expense Splitter</h1>

      <CreateExpense />
      <hr />
      <Balances />
    </div>
  );
}
