import React from 'react'
import {Route,Routes,useLocation}from 'react-router-dom'
import HomePage from '../customer/pages/HomePage/HomePage'
import Navigation from '../customer/components/navigation/Navigation'
import Cart from '../customer/components/Cart/Cart'
import Footer from '../customer/components/Footer/Footer'
import Product from '../customer/components/Product/Product'
import ProductDetails from '../customer/components/ProductDetails/ProductDetails'
import Checkout from '../customer/components/Checkout/Checkout'
import OrderDetails from '../customer/components/Order/OrderDetails'
import Order from '../customer/components/Order/Order'
const CustomerRoutes=()=>
{
    const location = useLocation();
    
  
    // Only show Navigation component when not on the NotFound page
    const showNavigation = location.pathname !== "*";
return (
    <div>
        <div>

        {showNavigation && <Navigation />}
        </div>
        <Routes>
        <Route path='/login' element={<HomePage/>}></Route>
        <Route path='/register' element={<HomePage/>}></Route>


            <Route path='/' element={<HomePage/>}></Route>
            <Route path='/cart' element={<Cart/>}></Route>
            <Route path='/:lavelOne/:lavelTwo/:lavelThree' element={<Product/>}></Route>
            <Route path='/product/:productId' element={<ProductDetails/>}></Route>
            <Route path='/checkout' element={<Checkout/>}></Route>
            <Route path='/account/order' element={<Order/>}></Route>
            <Route path='/account/order/:orderId' element={<OrderDetails/>}></Route>

        </Routes>
        <div>
            <Footer/>
        </div>
    </div>
)
}
export default CustomerRoutes 
