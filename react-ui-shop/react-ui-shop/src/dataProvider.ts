import simpleRestProvider from 'ra-data-simple-rest';
import { fetchUtils } from 'react-admin';
import { GetListParams } from 'react-admin';

const apiUrl = 'http://localhost:8080/api';
const httpClient = fetchUtils.fetchJson;

export const dataProvider = {
    // Spread in all default methods from the simpleRestProvider
    ...simpleRestProvider(apiUrl, httpClient),

    getList: (resource: string, params: GetListParams) => {
        // Destructure the params object to get pagination, sort, and filter
        const { pagination = { page: 1, perPage: 10 }, 
                sort = { field: 'id', order: 'ASC' },
                filter = {} } = params;
        const { page, perPage } = pagination;   
        const { field, order } = sort;

       
        const query = {
            page: page - 1, // Convert from React Admin's 1-based pagination to 0-based for Spring Boot
            size: perPage,  
            filterByName: filter.name || null, 
            sort: JSON.stringify([field, order]),
        };

        
        const url = `${apiUrl}/${resource}?${fetchUtils.queryParameters(query)}`;

        // Send the request to the API and process the response
        return httpClient(url).then(({ headers, json }) => ({
            data: json,
            total: parseInt(headers.get('Content-Range')?.split('/')?.pop() || '0', 10),
        }));
    },    
};