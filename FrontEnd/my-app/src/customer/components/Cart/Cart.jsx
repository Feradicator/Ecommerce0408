import React, { useEffect, useState } from 'react';
import CartItem from './CartItem';
import { Button, CircularProgress } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from "react-redux";
import { getCart } from '../../../State/Cart/Action';
import EmptyCart from './EmptyCart.png';
import AuthModal from '../../Auth/AuthModal';
import LoginForm from '../../Auth/LoginForm';

const Cart = () => {
  const [openAuthModal, setOpenAuthModal] = useState(false);
  const [openLoginForm, setOpenLoginForm] = useState(false);

  const navigate = useNavigate();
  const dispatch = useDispatch();

  const jwt = localStorage.getItem("jwt");

  const { cart, loading, cartItems } = useSelector(state => state.cart);

  // Fetch cart when jwt exists
  useEffect(() => {
    if (!jwt) return;
    dispatch(getCart(jwt));
  }, [jwt, dispatch]);

  // ---------- LOADING ----------
  if (loading) {
    return (
      <div 
        style={{
          height: "80vh",
          display: "flex",
          alignItems: "center",
          justifyContent: "center",
        }}
      >
        <CircularProgress />
      </div>
    );
  }

  // ---------- NOT LOGGED IN ----------
  if (!jwt) {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen">
        <div className="text-xl font-bold mb-4">Cart Empty</div>
        <img
          className="cursor-pointer mb-4"
          style={{ height: '25vh', width: 'auto' }}
          src={EmptyCart}
          alt="Empty Cart"
        />
        <div className="flex space-x-4">
          
          {/* LOGIN BUTTON */}
          <button
            className="px-4 py-2 bg-blue-500 text-white rounded hover:bg-blue-700"
            onClick={() => setOpenLoginForm(true)}
          >
            Login
          </button>
          {openLoginForm && <LoginForm onClose={() => setOpenLoginForm(false)} />}

          {/* SIGNUP BUTTON */}
          <button
            className="px-4 py-2 bg-green-500 text-white rounded hover:bg-green-700"
            onClick={() => setOpenAuthModal(true)}
          >
            Signup
          </button>
          <AuthModal open={openAuthModal} handleClose={() => setOpenAuthModal(false)} />
        </div>
      </div>
    );
  }

  // ---------- EMPTY CART ----------
  if (!cartItems || cartItems.length === 0) {
    return (
      <div className="flex flex-col items-center justify-center min-h-screen">
        <div className="text-xl font-bold mb-4">Cart Empty</div>
        <img
          style={{ height: '35vh', width: 'auto' }}
          src={EmptyCart}
          alt="Empty Cart"
        />
      </div>
    );
  }

  // ---------- CART UI ----------
  return (
    <div className="">
      <div className="lg:grid grid-cols-3 lg:px-16 relative">
        
        {/* LEFT: CART ITEMS */}
        <div className="lg:col-span-2 lg:px-5 bg-white">
          <div className="space-y-3">
            {cartItems.map(item => (
              <CartItem key={item.id} item={item} showButton={true} />
            ))}
          </div>
        </div>

        {/* RIGHT: PRICE SUMMARY */}
        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
          <div className="border p-5 bg-white shadow-lg rounded-md">
            <p className="font-bold opacity-60 pb-4">PRICE DETAILS</p>
            <hr />

            <div className="space-y-3 font-semibold">
              <div className="flex justify-between pt-3 text-black">
                <span>Price ({cart?.totalItem} items)</span>
                <span>₹{cart?.totalPrice}</span>
              </div>

              <div className="flex justify-between">
                <span>Discount</span>
                <span className="text-green-700">-₹{cart?.discounte}</span>
              </div>

              <div className="flex justify-between">
                <span>Delivery Charges</span>
                <span className="text-green-700">Free</span>
              </div>

              <hr />
              
              <div className="flex justify-between font-bold text-lg">
                <span>Total Amount</span>
                <span className="text-green-700">₹{cart?.totalDiscountedPrice}</span>
              </div>
            </div>

            <Button
              onClick={() => navigate("/checkout?step=2")}
              variant="contained"
              sx={{ padding: ".8rem 2rem", marginTop: "2rem", width: "100%" }}
            >
              Check Out
            </Button>
          </div>
        </div>

      </div>
    </div>
  );
};

export default Cart;
