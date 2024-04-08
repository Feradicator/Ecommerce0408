import React, { useState } from 'react'
import HomeSectionCard from '../HomeSectionCard/HomeSectionCard';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import { Button, TextField, AppBar } from '@mui/material';

import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
const HomeSectionCarosel = ({data,sectionName}) => {
    const [activeIndex1,setActiveIndex1]=useState(0);
    const responsive = {
        0: {
            items: 1
        },
        720: {
            items: 3
        },
        1024: {
            items: 5.5 ,
            itemsFit: 'contain'
        },
    };
    const slidePrev=()=>setActiveIndex1(activeIndex1-1);
    const slideNext=()=>setActiveIndex1(activeIndex1+1);
    const syncActiveIndex=({item})=>setActiveIndex1(item);
    const items = data.slice(0,10).map((item) => <HomeSectionCard product={item}/>);
    console.log(items.length);
    return (

        <div className=" border">
            <h2 className='text-2xl font-extrabold text-gray-800 py-5'>{sectionName}</h2>
            <div className="relative p-5 ">
                <AliceCarousel
                    items={items}
                   
                    responsive={responsive}
                   
                    onSlideChanged={syncActiveIndex}
                    activeIndex={activeIndex1}
                />
               {activeIndex1 !==items.length-5 && <Button variant="contained"  className="z-50" onClick={slideNext} sx={{ position: 'absolute', top: "8rem", right: "0rem", transform: "translateX(50%) rotate(90deg)" ,bgcolor:'white'}} aria-label="next">
                    <KeyboardArrowLeftIcon sx={{ transform: "rotate(90deg)" ,color:'black'}} />
                </Button>}
                {activeIndex1!==0 &&
                    <Button variant="contained" onClick={slidePrev} className="z-50" sx={{ position: 'absolute', top: "8rem", left: "0rem", transform: "translateX(-50%) rotate(90deg)" ,bgcolor:'white'}} aria-label="next">
                    <KeyboardArrowLeftIcon sx={{ transform: "rotate(-90deg)" ,color:'black'}} />
                </Button>}

            </div>
        </div>

    )
}
export default HomeSectionCarosel 
