import { useState } from "react";
import { getBalances } from "../api";

export default function Balances() {
  const [groupId, setGroupId] = useState("");
  const [balances, setBalances] = useState([]);

async function loadBalances() {
  try {
    const data = await getBalances(groupId);
    console.log("API response:", data);
    setBalances(data);
  } catch (e) {
    console.error("Error loading balances", e);
  }
}


  return (
    <div>
      <h2>Group Balances</h2>

      <input placeholder="Group ID" onChange={e => setGroupId(e.target.value)} />
      <button onClick={loadBalances}>Load</button>

      <ul>
        {balances.map((b, index) => (
          <li key={index}>
            {b.userName} : ₹{b.balance}
          </li>
        ))}
      </ul>
    </div>
  );
}
