import React from 'react'
import MainCarousel from '../../components/HomeCarosel/MainCarosel'
import HomeSectionCarosel from '../../components/HomeSectionCarosel/HomeSectionCarosel'
import {MainCaroselData }from '../../components/HomeCarosel/MainCaroselData'
import { useState } from 'react'

import { useEffect } from 'react';
import axios from 'axios';
import { API_BASE_URL } from '../../../config/apiConfig';
const HomePage=()=>
{
  const [menKurta, setMenKurta] = useState([]);
  const[menShoes,setMenShoes]=useState([]);
  const [dress,setDress]=useState([]);
  const[jeans,setJeans]=useState([]);
  const[top,setTop]=useState([]);
  const [loading, setLoading] = useState(true);
    useEffect(() => {
        const fetchData = async () => {
          try {
            console.log("yooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
            const category1='mens_kurta'
            const category2='mens_shoes'
            const category3='dress';
            const category4='top';
            const category5='men_jeans';
            const colors=[];
            const sizes=[];
            const minPrice=0;
            const maxPrice=10000;
            const minDiscount=0;
            const stock=null; 
           const sort="price_low";
            
            const pageNumber=0;
            const pageSize=11;
            const response1 = await axios.get(
              `${API_BASE_URL}/products?color=${colors}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category1}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
            );
            
           
            const dataresponse1=response1.data.content;
            console.log(dataresponse1);
            // Do something with the data
            setMenKurta(dataresponse1);
            const response2 = await axios.get(
              `${API_BASE_URL}/products?color=${colors}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category2}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
            );
            const dataresponse2=response2.data.content;
            setMenShoes(dataresponse2);

            const response3= await axios.get(
              `${API_BASE_URL}/products?color=${colors}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category3}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
            );
            const dataresponse3=response3.data.content;
            setDress(dataresponse3);

            const response4= await axios.get(
              `${API_BASE_URL}/products?color=${colors}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category4}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
            );
            const dataresponse4=response4.data.content;
            setTop(dataresponse4);


            const response5= await axios.get(
              `${API_BASE_URL}/products?color=${colors}&size=${sizes}&minPrice=${minPrice}&maxPrice=${maxPrice}&minDiscount=${minDiscount}&category=${category5}&stock=${stock}&sort=${sort}&pageNumber=${pageNumber}&pageSize=${pageSize}`
            );
            const dataresponse5=response5.data.content;
            setJeans(dataresponse5);
            setLoading(false);

           console.log(menKurta)
            console.log("yooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");


            
          } catch (error) {
            console.error('Error fetching data:', error);
          }
        };
    
        fetchData();
      }, []); 

      if (loading) return <div>Loading...</div>;
return (
    <div>
        <MainCarousel images={MainCaroselData}/>

        <div className='space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10'>
        <HomeSectionCarosel data={menKurta} sectionName={"Men's Kurta"} />   
        <HomeSectionCarosel data={menShoes} sectionName={"Men's Shoes"} />
       
        <HomeSectionCarosel data={top} sectionName={"Women Tops"} />
        <HomeSectionCarosel data={dress} sectionName={"Women Dress"} />
        <HomeSectionCarosel data={jeans} sectionName={"Jeans"} />
        {/* <HomeSectionCarosel data={kurtaPage1} sectionName={"Women's Kurtas"} /> */}
        
        </div>
    </div>

)
}
export default HomePage 
