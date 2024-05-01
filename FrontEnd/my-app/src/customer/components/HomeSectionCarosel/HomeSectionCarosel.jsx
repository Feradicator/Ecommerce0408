import React, { useState } from 'react'
import HomeSectionCard from '../HomeSectionCard/HomeSectionCard';
import AliceCarousel from 'react-alice-carousel';
import 'react-alice-carousel/lib/alice-carousel.css';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import { Button, TextField, AppBar } from '@mui/material';

import KeyboardArrowLeftIcon from '@mui/icons-material/KeyboardArrowLeft';
const HomeSectionCarosel = ({sectionName,data}) => {
    const [activeIndex,setActiveIndex]=useState(0);
    const responsive = {
        0: {
          items: 2,
          itemsFit: "contain",
        },
        568: {
          items: 3,
          itemsFit: "contain",
        },
        1024: {
          items: 5.5,
          itemsFit: "contain",
        },
      };
      const slidePrev = () => {
        if (activeIndex > 0) {
          setActiveIndex(activeIndex - 1);
        } else {
          setActiveIndex(items.length - 1); // Loop back to the last item if at the beginning
        }
      };
      
      const slideNext = () => {
        if (activeIndex < items.length - 1) {
          setActiveIndex(activeIndex + 1);
        } else {
          setActiveIndex(0); // Loop back to the first item if at the end
        }
      };
      
    const syncActiveIndex=({item})=>setActiveIndex(item);
    const items = data?.slice(0, 10).map((item) => (
    <div className="">
      {" "}
      <HomeSectionCard product={item} />
    </div>
  ));
    console.log(items.length);
    return (

        <div className=" border">
            <h2 className='text-2xl font-extrabold text-gray-800 py-5'>{sectionName}</h2>
            <div className="relative p-5 ">
                <AliceCarousel
               
                    items={items}
                    mouseTracking
                    responsive={responsive}
                    animationType="fadeout"
                    
                    onSlideChanged={syncActiveIndex}
                    activeIndex={activeIndex}
                />
               {activeIndex!==items.length-5 && (<Button variant="contained"  className="z-50" onClick={slideNext} sx={{ position: 'absolute', top: "8rem", right: "0rem", transform: "translateX(50%) rotate(90deg)" ,bgcolor:'white'}} aria-label="next">
                    <KeyboardArrowLeftIcon sx={{ transform: "rotate(90deg)" ,color:'black'}} />
                </Button>)}
                {activeIndex!==0 &&(
                    <Button variant="contained" onClick={slidePrev} className="z-50" sx={{ position: 'absolute', top: "8rem", left: "0rem", transform: "translateX(-50%) rotate(90deg)" ,bgcolor:'white'}} aria-label="next">
                    <KeyboardArrowLeftIcon sx={{ transform: "rotate(-90deg)" ,color:'black'}} />
                </Button>)}

            </div>
        </div>

    )
}
export default HomeSectionCarosel 

