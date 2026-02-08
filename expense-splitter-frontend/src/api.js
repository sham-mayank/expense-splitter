const BASE_URL = "http://localhost:8080";

export async function createExpense(data) {
  const res = await fetch(`${BASE_URL}/api/expenses`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(data),
  });
  return res.json();
}

export async function getBalances(groupId) {
  const res = await fetch(`${BASE_URL}/api/balances/${groupId}`);
  return res.json();
}
