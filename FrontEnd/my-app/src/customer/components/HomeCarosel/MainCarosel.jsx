import React from 'react';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import { MainCaroselData } from './MainCaroselData';




const MainCarousel = () => {
    const items = MainCaroselData.map((item)=><img className='cursor-pointer' src={item.image} alt="" role='presentation'/>)

    return( 
    <AliceCarousel
    items={items}
    disableButtonsControls
    autoPlay
    autoPlayInterval={800}
    infinite
    
 
    />
)}

export default MainCarousel;