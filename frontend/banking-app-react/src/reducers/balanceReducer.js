const initialState = 0;
import { TYPES } from '../store/actions';

function reducer(state = initialState, action) {
  switch (action.type) {
    case TYPES.WITHDRAW:
      return  action.account;
    case TYPES.DEPOSIT:
      return  action.account;
  }
  return state;
}
export default reducer;
