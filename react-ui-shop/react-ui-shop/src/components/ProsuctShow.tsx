import { Show, SimpleShowLayout, TextField, NumberField, ImageField } from 'react-admin';

const ProductShow = () => (
  <Show>
    <SimpleShowLayout>
      <TextField source="id" />
       <ImageField source="image_path" label="Product Image" sortable={false}  />
      <TextField source="name" />
      <TextField source="description" />
      <NumberField source="price" />
      <NumberField source="stock_quantity" />
    </SimpleShowLayout>
  </Show>
);

export default ProductShow;
