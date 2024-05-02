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
import SearchProduct from '../customer/components/Product/SearchProduct'
import About from '../customer/pages/HomePage/About'
import Contact from '../customer/pages/HomePage/Contact'
import PrivacyPolicy from '../customer/pages/HomePage/PrivacyPolicy'
import TearmsCondition from '../customer/pages/HomePage/TearmsCondition'
import RateProduct from '../customer/components/ReviewProduct/RateProduct'
import PaymentSuccess from '../customer/components/paymentSuccess/PaymentSuccess'
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


        <Route path="/" element={<HomePage />}></Route>
        <Route path="/products/search" element={<SearchProduct/>}></Route>
        <Route path="/home" element={<HomePage />}></Route>
        <Route path="/about" element={<About />}></Route>
        <Route path="/privaciy-policy" element={<PrivacyPolicy />}></Route>
        <Route path="/terms-condition" element={<TearmsCondition />}></Route>
        <Route path="/contact" element={<Contact />}></Route>
        <Route path="/:lavelOne/:lavelTwo/:lavelThree" element={<Product />}></Route>
        <Route path="/product/:productId" element={<ProductDetails />}></Route>
        <Route path="/cart" element={<Cart />}></Route>
        <Route path="/account/order" element={<Order />}></Route>
        <Route path="/account/order/:orderId" element={<OrderDetails />}></Route>
        <Route path="/account/rate/:productId" element={<RateProduct />}></Route>
        <Route path="/checkout" element={<Checkout />}></Route>
        <Route path="/payment/:orderId" element={<PaymentSuccess />}></Route>

        </Routes>
        <div>
            <Footer/>
        </div>
    </div>
)
}
export default CustomerRoutes 
