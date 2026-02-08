import { useState } from "react";
import { createExpense } from "../api";

export default function CreateExpense() {
  const [description, setDescription] = useState("");
  const [amount, setAmount] = useState("");
  const [groupId, setGroupId] = useState("");
  const [paidBy, setPaidBy] = useState("");

  async function handleSubmit() {
    await createExpense({
      description,
      totalAmount: amount,
      groupId,
      paidByUserId: paidBy,
    });

    alert("Expense added!");
  }

  return (
    <div>
      <h2>Create Expense</h2>

      <input placeholder="Description" onChange={e => setDescription(e.target.value)} />
      <input placeholder="Amount" type="number" onChange={e => setAmount(e.target.value)} />
      <input placeholder="Group ID" onChange={e => setGroupId(e.target.value)} />
      <input placeholder="Paid By User ID" onChange={e => setPaidBy(e.target.value)} />

      <button onClick={handleSubmit}>Add Expense</button>
    </div>
  );
}
