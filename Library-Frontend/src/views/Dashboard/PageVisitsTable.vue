<template>
  <div class="card">
    <div class="card-header border-0">
      <div class="row align-items-center">
        <div class="col">
          <h3 class="mb-0">Librarian Schedule</h3>
        </div>
      </div>
    </div>

    <div class="table-responsive">
      <base-table thead-classes="thead-light" :data="tableData">
        <template v-slot:columns>
          <th>Librarian</th>
          <th>Monday</th>
          <th>Tuesday</th>
          <th>Wednesday</th>
          <th>Thursday</th>
          <th>Friday</th>
          <th>Saturday</th>
        </template>

        <template v-slot:default="row">
          <th scope="row">
            {{ row.item.name }}
          </th>
          <td>
            {{ row.item.monday }}
          </td>
          <td>
            {{ row.item.tuesday }}
          </td>
          <td>
            {{ row.item.wednesday }}
          </td>
          <td>
            {{ row.item.thursday }}
          </td>
          <td>
            {{ row.item.friday }}
          </td>
          <td>
            {{ row.item.saturday }}
          </td>
        </template>
      </base-table>
    </div>
  </div>
</template>
<script>
export default {
  name: "page-visits-table",
  data() {
    return {
      tableData: [
        {
          name: "Aziz",
          monday: "9-22",
          tuesday: "7-12",
          wednesday: "7-5",
          thursday: "-",
          friday:"5-22",
          saturday:"11-16",
        },
        {
          name: "Simo",
          monday: "9-22",
          tuesday: "7-12",
          wednesday: "7-5",
          thursday: "-",
          friday:"5-22",
          saturday:"11-16",
        },
        {
          name: "Alex",
          monday: "9-22",
          tuesday: "7-12",
          wednesday: "7-5",
          thursday: "-",
          friday:"5-22",
          saturday:"11-16",
        },
        {
          name: "Aly",
          monday: "9-22",
          tuesday: "7-12",
          wednesday: "7-5",
          thursday: "-",
          friday:"5-22",
          saturday:"11-16",
        },
        {
          name: "Jewoo",
          monday: "9-22",
          tuesday: "7-12",
          wednesday: "7-5",
          thursday: "-",
          friday:"5-22",
          saturday:"11-16",
        },
      ],
    };
  },
};
</script>
<style></style>
