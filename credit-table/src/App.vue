
<template>
  <div>
    <DataTable :value="credits" ref="dt" paginator :rows="100" tableStyle="min-width: 50rem">
      <template #header>
        <div style="text-align: left">
          <Button icon="pi pi-external-link" label="Export" @click="exportCSV($event)" />
        </div>
      </template>
      <Column field="id" header="ID" exportHeader="Credit ID" sortable ></Column>
      <Column field="createdDate" header="Created Date" sortable ></Column>
      <Column field="status" header="Status" sortable ></Column>
      <Column field="amount" header="Amount" sortable ></Column>
      <Column field="openDate" header="Open Date" sortable ></Column>
      <Column field="closeDate" header="Close Date" sortable ></Column>
      <Column field="customerId" header="Customer ID" sortable ></Column>
    </DataTable>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useToast } from 'primevue/usetoast';
const toast = useToast();
onMounted(() => {
  getUsers();
});

const dt = ref();
const credits = ref();
const exportCSV = () => {
  dt.value.exportCSV();
};

const getUsers = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/credits');
    credits.value = response.data;
  } catch (error) {
    console.error('Ошибка при загрузке данных:', error);
    toast.add({
      severity: 'error',
      summary: 'Ошибка',
      detail: 'Не удалось загрузить кредиты',
      life: 3000,
    });
  }
};
</script>
