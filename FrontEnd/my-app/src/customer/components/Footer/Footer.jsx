
import React from 'react';
import logo from "./logo.png";

const Footer = () => {
  return (
    <footer className="text-gray-300 py-4 mt-8" style={{ backgroundColor: '#191C27' }}> {/* Use inline style */}
      <div className="container mx-auto flex flex-col md:flex-row items-center justify-between">
        <div className="flex items-center mb-4 md:mb-0">
          <img src={logo} alt="Your Logo" className="h-8 mr-2" />
          <span>Shivam Yadav</span>
        </div>
        <div>
          <span className="text-sm font-semibold">Copyright © 2024 Shivam Yadav. All Rights Reserved.</span>
        </div>
        <div className="text-sm">
          <span className="block mb-2 md:inline-block md:mb-0 mr-4">Email: yadavshivam042001@gmail.com</span>
          <span className="block">Phone: +919717219842</span>
        </div>
      </div>
    </footer>
  );
};

export default Footer;
