import {
  Admin,
  Resource,
} from "react-admin";
import { Layout } from "./Layout";
import { dataProvider } from "./dataProvider";
import ProductList from './components/ProductList';
import ProductEdit from './components/ProductEdit';
import ProsuctShow from './components/ProsuctShow';

export const App = () => (
  <Admin
    layout={Layout}
    dataProvider={dataProvider}
   
  >
    <Resource
      name="products"
      list={ProductList}
      edit={ProductEdit}
      show={ProsuctShow}
      options={{ undoable: false }}
    />
  </Admin>
);
