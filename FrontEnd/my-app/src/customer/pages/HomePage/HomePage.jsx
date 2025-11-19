import React, { useState, useEffect } from 'react';
import MainCarousel from '../../components/HomeCarosel/MainCarosel';
import HomeSectionCarosel from '../../components/HomeSectionCarosel/HomeSectionCarosel';
import { MainCaroselData } from '../../components/HomeCarosel/MainCaroselData';

import axios from 'axios';
import { API_BASE_URL } from '../../../config/apiConfig';
const HomePage = () => {
  const [menKurta, setMenKurta] = useState([]);
  const [menShoes, setMenShoes] = useState([]);
  const [dress, setDress] = useState([]);
  const [top, setTop] = useState([]);
  const [jeans, setJeans] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const categories = ['mens_kurta', 'men_shoes', 'women_dress', 'women_jeans', 'men_jeans'];
        const colors = [];
        const sizes = [];
        const minPrice = 0;
        const maxPrice = 10000;
        const minDiscount = 0;
        const stock = null;
        const sort = "price_low";
        const pageNumber = 1;
        const pageSize = 11;

        // Create an array of promises for all the requests
        const requests = categories.map(category => {
          const params = {
            color: colors.join(',') || '',
            size: sizes.join(',') || '',
            minPrice,
            maxPrice,
            minDiscount,
            category,
            stock: stock !== null ? stock : '',
            sort,
            pageNumber,
            pageSize
          };

      

          return axios.get(`${API_BASE_URL}/products`, { params });
        });

        // Wait for all the requests to complete
        const responses = await Promise.all(requests);

        // Log all responses for debugging
        responses.forEach((response, index) => {
          console.log(`Response ${index + 1}:`, response);
        });

        // Extract data from the responses
        const [dataresponse1, dataresponse2, dataresponse3, dataresponse4, dataresponse5] = responses.map(response => response.data.content || []);

        // Set the data in state
        setMenKurta(dataresponse1);
        setMenShoes(dataresponse2);
        setDress(dataresponse3);
        setTop(dataresponse4);
        setJeans(dataresponse5);

        setLoading(false);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };

    fetchData();
  }, []);

  if (loading) {
    return (
      <div className="flex items-center justify-center min-h-screen">
        <div className="w-16 h-16 border-4 border-dashed rounded-full animate-spin border-blue-500"></div>
      </div>
    );
  }

  return (
    <div>
      <MainCarousel images={MainCaroselData} />
      <div className='space-y-10 py-20 flex flex-col justify-center px-5 lg:px-10'>
        <HomeSectionCarosel data={menKurta} sectionName={"Men's Kurta"} />
        <HomeSectionCarosel data={menShoes} sectionName={"Men's Shoes"} />
        <HomeSectionCarosel data={top} sectionName={"Women Jeans"} />
        <HomeSectionCarosel data={dress} sectionName={"Women Dress"} />
        <HomeSectionCarosel data={jeans} sectionName={"Men Jeans"} />
      </div>
    </div>
  );
}

export default HomePage;
