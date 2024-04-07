import React from 'react'
const HomeSectionCard=()=>
{
return (
    <div className='cursor-pointer flex flex-col items-center bg-white rounded-lg overflow-hidden
     w-[15rem] mx-3'>
        <div className='h-[13rem] w-[10rem]'>
            <img className='object-cover object-top w-full h-full' src='https://www.ethnicplus.in/media/catalog/product/cache/1d5df636cf8c8988ea2d2c570bb7c21d/s/l/sls-rjp-mgs-185008_0_.jpg'
            alt=""/>

        </div>
        <div className='p-4'>
            <h3 className='text-lg front-medium text-gray-900'>
            Alluring Turquoise Zari Weaving
            </h3>
            <p className='mt-2 text-sm text-gray-500'>
            Saree With Blous e
            </p>

        </div>
    </div>
)
}
export default HomeSectionCard
