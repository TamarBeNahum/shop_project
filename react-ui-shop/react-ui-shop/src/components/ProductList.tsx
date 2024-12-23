import { List, Datagrid, TextField, NumberField, EditButton, ImageField, TextInput } from 'react-admin';
import '../customStyles.css';

const ProductFilter = [
    <TextInput label="Search by name" source="name" alwaysOn />
];

const ProductList = () => (
  <List filters={ProductFilter} >
    <Datagrid bulkActionButtons={false} >
      <TextField source="id" label="ID" sortable={true} /> 
      <ImageField source="image_path" label="Product Image" sortable={false}  />
      <TextField source="name" label="Product Name" />
      <TextField source="description" label="Description" />
      <NumberField source="price" label="Price" options={{ style: 'currency', currency: 'ILS' }} />
      <NumberField source="stock_quantity" label="Stock Quantity" sortable={false}/>
      <EditButton />
    </Datagrid>
  </List>
);
 
export default ProductList; 