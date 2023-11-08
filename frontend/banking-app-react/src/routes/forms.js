import React, {useState} from 'react';
import { ReactRedux, useDispatch } from 'react-redux';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardHeader from '@material-ui/core/CardHeader';
import { CardContent, Hidden } from '@material-ui/core';
import InputAdornment from '@material-ui/core/InputAdornment';
import Button from '@material-ui/core/Button';
import { Formik, Form } from 'formik';
import { object, number, string } from 'yup';
import { useSnackbar } from 'notistack';
import { useHistory } from 'react-router-dom';
import { deposit, withdraw, accountAction, transactionAction } from '../store/actions';
import { useField } from 'formik';
import { TextField } from '@material-ui/core';


// const initialValues = {};

function MyTextInput({ label, placeholder, value, onChangeValue, ...props }) {
  const [field, meta] = useField(props);
  const errorText = meta.error && meta.touched ? meta.error : '';
  return (
    <TextField
      {...field}
      error={meta.touched && !!meta.error}
      helperText={errorText}
      label={label}
      placeholder={placeholder}
      value={value}
      onChange={onChangeValue}
    />
  );
}

function MainForm({ heading, inputLabel1, inputLabel2, typeOperation, submitLabel, accountId, onSubmit }) {

  const initialVal = {
    nameTaker: '',
    amount: '',
    typeOperation: typeOperation,
    account: accountId,
    isAccount: typeOperation == "ACCOUNT" ? true : false
  }

  const [allValues, setAllValues] = useState(initialVal);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAllValues({
      ...allValues,
      [name]: value,
    });
  };


  return (
    <Formik

      initialValues={initialVal}
      onSubmit={(values, { setSubmitting }) => {
        onSubmit(allValues.amount, allValues.account, allValues.typeOperation, allValues.nameTaker);
        setSubmitting(false);
      }}

    >
      <Form>
        <Card variant="outlined">
          <CardHeader title={heading} />
          <CardContent>
            {typeOperation == 'ACCOUNT' && <div>
              <MyTextInput
                label='Taker of Account'
                name='nameTaker'
                type="text"
                value={allValues.nameTaker}
                onChangeValue={handleChange}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">{'\u20B9'}</InputAdornment>
                  )
                }}
              />
              </div>}
            {typeOperation != 'ACCOUNT' && <div>
              <MyTextInput
                label={inputLabel1}
                name='account'
                type="text"
                value={allValues.account}
                onChangeValue={handleChange}
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">{'\u20B9'}</InputAdornment>
                  )
                }}
              />
              <MyTextInput
                label={inputLabel2}
                name='amount'
                type="text"
                value={allValues.amount}
                onChangeValue={handleChange}
                placeholder="100"
                InputProps={{
                  startAdornment: (
                    <InputAdornment position="start">{'\u20B9'}</InputAdornment>
                  )
                }}
                
              />
            </div>}
            
          </CardContent>
          <CardActions>
            <Button variant="contained" color="primary" type="submit">
              {submitLabel}
            </Button>
          </CardActions>
        </Card>
      </Form>
    </Formik>
  );
}


function Deposit() {
  const { enqueueSnackbar } = useSnackbar();
  const history = useHistory();
  const dispatch = useDispatch();
  const onSubmit = async (amount, account, typeOperation, nameTaker) => {
    const response = await transactionAction(account, amount, typeOperation);
    dispatch(deposit(amount, account, typeOperation));
    if (!response){
      enqueueSnackbar(`You have to fill out all the fields`, { variant: 'error' });
    }else{
      enqueueSnackbar(`$ ${amount} deposited successfully for ${response.accountId}`);}
    
    history.push('/');
  };
  return MainForm({
    heading: 'Deposit Form',
    inputLabel1: 'Account to deposit',
    inputLabel2: 'Amount',
    typeOperation: 'DEPOSIT',
    submitLabel: 'Add Money',
    accountId: '',
    onSubmit: onSubmit
  });
}

function Withdraw() {
  const { enqueueSnackbar } = useSnackbar();
  const history = useHistory();
  const dispatch = useDispatch();
  const onSubmit = async (amount, account, typeOperation, nameTaker) => {
    const response = await transactionAction(account, amount, typeOperation);
    dispatch(withdraw(amount, account, typeOperation));
    if (!response){
      enqueueSnackbar(`You have to fill out all the fields`, { variant: 'error' });
    }else{
      enqueueSnackbar(`$ ${amount} withdrawn successfully for ${response.accountId}`);
    }
    
    history.push('/');
  };
  return MainForm({
    heading: 'Withdrawl Form',
    inputLabel1: 'Account to debit',
    inputLabel2: 'Amount',
    typeOperation: 'DEBIT',
    submitLabel: 'Get Money',
    accountId: '',
    onSubmit: onSubmit
  });
}

function Account() {

    const { enqueueSnackbar } = useSnackbar();
    const history = useHistory();
    const dispatch = useDispatch();
    const unique_id = Date.now()
    const  onSubmit = async (amount, account, typeOperation, nameTaker) => {
      const response = await (accountAction(account, nameTaker));
      dispatch(deposit(amount, account, typeOperation));
      if (!response){
        enqueueSnackbar(`You have to fill out all the fields`, { variant: 'error' });
      }else{
        enqueueSnackbar(`Accoount succesfuly creted with ID: ${account} Account ${response}`);}
      history.push('/');
    };
    return MainForm({
      heading: 'Create Account Form',
      inputLabel1: 'Name',
      inputLabel2: '',
      typeOperation: 'ACCOUNT',
      submitLabel: 'Create',
      accountId: unique_id,
      onSubmit: onSubmit
  });


}

export { Account, Deposit, Withdraw};
