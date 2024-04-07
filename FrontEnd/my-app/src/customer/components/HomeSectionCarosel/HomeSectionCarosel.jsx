import React from 'react'
import HomeSectionCard from '../HomeSectionCard/HomeSectionCard';
import AliceCarousel from 'react-alice-carousel';   
import 'react-alice-carousel/lib/alice-carousel.css';
const HomeSectionCarosel=()=>
{
    const responsive = {
        0: { 
            items: 1
        },
        720: { 
            items: 3
        },
        1024: {
            items: 5.5, 
            itemsFit: 'contain'
        },
    };
    const items=[1,1,1,1,1,1].map((item)=><HomeSectionCard/ >)
return (
    
    <div>
        <AliceCarousel
    items={items}
    disableButtonsControls
    autoPlay
    autoPlayInterval={800}
    infinite
    responsive={responsive}
    />
    </div>
)
}
export default HomeSectionCarosel 
