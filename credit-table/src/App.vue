
<template>
  <div>
    <DataTable :value="products" ref="dt" tableStyle="min-width: 50rem">
      <template #header>
        <div style="text-align: left">
          <Button icon="pi pi-external-link" label="Export" @click="exportCSV($event)" />
        </div>
      </template>
      <Column field="code" header="Code" exportHeader="Product Code"></Column>
      <Column field="name" header="Name"></Column>
      <Column field="category" header="Category"></Column>
      <Column field="quantity" header="Quantity"></Column>
    </DataTable>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { ProductService } from '@/service/ProductService';

onMounted(() => {
  ProductService.getProductsMini().then((data) => (products.value = data));
});

const dt = ref();
const products = ref();
const exportCSV = () => {
  dt.value.exportCSV();
};
</script>
