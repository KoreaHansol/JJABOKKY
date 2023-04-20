<template>
  <div class="selectbox-container" :class="{ left, right }" ref="insideRootElement" @click="openSelectBox = true">
    <div class="select-value">
      <slot class="slot"></slot>
    </div>

    <div class="select-items" v-if="openSelectBox">
      <div class="item" :class="{ 'selected': selectedItemId == data.id }" v-for="data in datas" :key="data.id" @click="onChangeItem( data )">{{ data.text }}</div>
    </div>
  </div>
</template>
<script>
import lodash from 'lodash'
import outsideClick from '@/mixins/outsideClick'

export default {
  name: 'SelectBox',
  model: {
    prop: 'modelValue',
    event: 'onChange'
  },
  props: {
    datas: [],
    left: false,
    right: false
  },
  mixins: [ outsideClick ],
  data() {
    return {
      openSelectBox: false,
      selectedItem: null
    }
  },
  computed: {
    selectedItemId() {
      return _.get( this.selectedItem, 'id', null )
    }
  },
  created() {
    
  },
  methods: {
    onChangeItem( data ) {
      this.selectedItem = data
      this.$emit( 'onChange', data )
      this.openSelectBox = false
    },
    onOutsideClick() {
      this.openSelectBox = false
    }
  }
}
</script>

<style scoped lang="scss"> 
.selectbox-container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  width: fit-content;
  height: fit-content;
  background-color: #ffffff;
  padding: 10px;
  border-radius: 6px;

  &.left {
    border-radius: 6px 0 0 6px;
  }

  &.right {
    border-radius: 0 6px 6px 0;
  }

  .select-value {
    display: flex;
    justify-content: center;
    align-items: center;
  }


  border: 1px solid #e9e9e9;
  cursor: pointer;

  .select-items {
    position: absolute;
    right: 0;
    top: 50px;

    display: flex;
    width: max-content;
    height: fit-content;
    flex-direction: column;
    background-color: #ffffff;
    border-radius: 6px;
    border: 1px solid #E9E9E9;

    .item {
      min-width: 130px;
      padding: 10px 20px;
      &.selected {
        color: blue;
      }
    }
  }
}
</style>
