<template>
  <div class="card">
    <div class="card-header border-0">
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0">Library Schedule</h3>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table thead-classes="thead-light" :data="tableData">
        <template v-slot:columns>
          <th>Day</th>
          <th>Time</th>
        </template>

        <template v-slot:default="row">
          <th scope="row">
            {{ row.item.day }}
          </th>
          <td>
            {{ row.item.time }}
          </td>
        </template>
      </base-table>
    </div>
  </div>
</template>
<script>
export default {
  name: "social-traffic-table",
  data() {
    return {
      tableData: [
        {
          day: "Monday",
          time: "7-22",
        },
        {
          day: "Tuesday",
          time: "7-22",
        },
        {
          day: "Wednesday",
          time: "7-22",
        },
        {
          day: "Wednesday",
          time: "7-22",
        },
        {
          day: "Thursday",
          time: "7-22",
        },
        {
          day: "Friday",
          time: "7-22",
        },
        {
          day: "Saturday",
          time: "7-22",
        },
        {
          day: "Sunday",
          time: "closed",
        },
      ],
    };
  },
};
</script>
<style></style>
