const DEPOSIT = 'DEPOSIT';
const WITHDRAW = 'WITHDRAW';
export const TYPES = {
  DEPOSIT,
  WITHDRAW
};
export function withdraw(amount, account, typeOperation) {
  return { type: WITHDRAW, account, account };
}
export function deposit(amount, account, typeOperation) {
  return { type: DEPOSIT, account, account };
}

export async function transactionAction(accountId, amount, typeOperation){
  return fetch('http://transaction-service:8081/v1/transactions/', {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*"
        },
        body: JSON.stringify({
          accountId: accountId,
          type: typeOperation,
          amount: amount
        })}).then(response => response.json())
          .then(data => {
          return data;
        })
        .catch(error => {
          console.error(error);
          return null;
        })
    
  
}


export async function accountAction (accountId, nameTaker) {

  return fetch('http://account-service:8080/v1/accounts/', {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "Access-Control-Allow-Origin": "*"
        },
        body: JSON.stringify({
          id: accountId,
          name: nameTaker,
          balance: 0.0,
          version: 0 
        })}).then(response => response.json())
          .then(data => {
          return data;
        })
        .catch(error => {
          console.error(error);
          return null;
        })
    
  
}


export async function balanceAction (accountId) {

  return fetch(`http://account-service:8080/v1/accounts/${accountId}/balance`).then(response => response.json())
          .then(data => {
          return data;
        })
        .catch(error => {
          console.error(error);
          return error;
        })
    
  
}

