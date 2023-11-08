import React, {useState, useEffect} from 'react';
import ReactRedux from 'react-redux';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import { CardContent } from '@material-ui/core';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

function Home() {
  const balance = useSelector(state => state.balance);

  const [data, setData] = useState('');
  const [inputValue, setInputValue] = useState('');

  useEffect(() => {
    fetch(`http://localhost:8080/v1/accounts/${balance}/balance`)
      .then(response => response.json())
      .then(data => {
        setData(data); // Update the state with the fetched data
        setInputValue(data.balance); // Set the input field value with the data
      })
      .catch(error => console.error('Error fetching data:', error));
  }, []); 

  const handleInputChange = (e) => {
    setInputValue(e.target.value);
  };

  return (
    <Card>
      <CardContent>
        <Typography color="textSecondary" gutterBottom={true}>
          Hello User
        </Typography>

        <Typography variant="h5" component="h2">
          Your balance is {'\u0024'} {data.balance}
        </Typography>
      </CardContent>
      <CardActions>
        <Button color="secondary" component={Link} to="/account">
          Account
        </Button>

        <Button color="secondary" component={Link} to="/deposit">
          Deposit
        </Button>

        <Button color="secondary" component={Link} to="/withdraw">
          Withdraw
        </Button>
      </CardActions>
    </Card>
  );
}
export default Home;
