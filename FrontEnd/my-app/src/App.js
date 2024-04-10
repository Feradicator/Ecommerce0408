import logo from './logo.svg';
import './App.css';
import Navigation from './customer/components/navigation/Navigation';
import HomePage from './customer/pages/HomePage/HomePage';
import Footer from './customer/components/Footer/Footer';
import Product from './customer/components/Product/Product';
import ProductDetails from './customer/components/ProductDetails/ProductDetails';
import Cart from './customer/components/Cart/Cart';
import Checkout from './customer/components/Checkout/Checkout';
import Order from './customer/components/Order/Order';
import OrderDetails from './customer/components/Order/OrderDetails';
import {Route,Routes}from 'react-router-dom'
import CustomerRoutes from './Routes/CustomerRoutes';
function App() {
  return (
    <div className="App">
      <Routes>
        <Route path='/*' element={<CustomerRoutes/>}></Route>
      </Routes>
      
      {/* <Navigation/> */}
      {/* <div>
      <HomePage/>
      </div> */}
      {/* <div>
        <Product/>
      </div> */}
      {/* <div>
        <ProductDetails/>
      </div> */}
      {/* <div>
        <Cart/>
      </div> */}
      {/* <div>
        <Checkout/>
      </div> */}
      {/* <div>
        <Order/>
      </div> */}
      {/* <div>
        <OrderDetails/>
      </div>
      <Footer/> */}
    </div>
  );
}

export default App;