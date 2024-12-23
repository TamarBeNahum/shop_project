import { Edit, SimpleForm, TextInput, NumberInput } from 'react-admin';

const ProductEdit = () => (
  <Edit >
    <SimpleForm>
      <TextInput source="id" disabled />
      <TextInput source="name" />
      <TextInput source="description" />
      <NumberInput source="price" />
      <NumberInput source="stock_quantity" />
    </SimpleForm>
  </Edit>
);

export default ProductEdit;
