<template>
  <div class="normal-input-wrapper" :class="{'has-outline': hasOutline, 'disabled':disabled}">
    <div class="type-text-wrapper" :class="{button: iconButtonEnabled}" v-if="type==='text'">
      <input :type="inputType" class="pure-input" :class="{'number-type-arrow-hidden':numberTypeArrowHidden}"
             :disabled="disabled" :placeholder="placeholder"
             @focus="onFocus(value)"
             @blur="isFocused = false"
             :value="value"
             @input="onInput"
             @change="$emit('change', $event)"
             @keyup.enter="onEnter(value)"/>
      <div v-if="suffix" class="suffix-area">
        {{suffix}}
      </div>
      <div v-if="rightSideIcon && !iconButtonEnabled" class="icon-area">
        <s-icon>{{rightSideIcon}}</s-icon>
      </div>
      <div v-else-if="rightSideIcon && iconButtonEnabled" class="icon-area button"
           v-touch-support @click="$emit('iconButtonClick', value)">
        <s-icon>{{rightSideIcon}}</s-icon>
      </div>
    </div>
    <label class="type-select-wrapper" v-else-if="type ==='select'">
      <div v-if="rightSideIcon" class="icon-area">
        <s-icon>{{rightSideIcon}}</s-icon>
      </div>
      <div v-if="placeholder && !value" class="select-placeholder">{{placeholder}}</div>
      <select class="pure-select" :disabled="disabled" :id="'normal-input-select_' + privateId" :value="value"
              @input="onInput" @change="$emit('change', $event)">
        <option v-for="option in selectOptions" class="select-option"
                :key="option[selectOption.value] + option[selectOption.name]"
                :value="selectOption.value ? option[selectOption.value] : option">{{
            option[selectOption.name] }}
        </option>
      </select>
    </label>
    <div class="type-readonly-wrapper" v-else-if="type==='readonly'" v-touch-support @click="$emit('click', value)">
      <div v-if="placeholder && !value" class="readonly-placeholder">{{placeholder}}</div>
      <div type="text" class="readonly-value-text">{{value}}</div>
      <div v-if="suffix" class="icon-area">
        {{suffix}}
      </div>
      <div v-if="rightSideIcon" class="icon-area">
        <s-icon>{{rightSideIcon}}</s-icon>
      </div>
    </div>
  </div>
</template>
<script>

let privateId = 0

export default {
  name: 'normalInput',
  model: {
    props: 'value',
    event: 'input'
  },
  props: {
    value: {
      type: [String, Number],
      default: () => {
        return ''
      }
    },
    suffix: {
      type: [String, Number],
      default: () => {
        return ''
      }
    },
    numberTypeArrowHidden: {
      type: Boolean,
      default: () => {
        return false
      }
    },
    disabled: {
      type: Boolean,
      default: () => {
        return false
      }
    },
    placeholder: {
      type: String,
      default: () => {
        return ''
      }
    },
    iconButtonEnabled: {
      type: Boolean,
      default: () => {
        return false
      }
    },
    maxLength: {
      type: Number,
      default: () => {
        return null
      }
    },
    inputType: {
      type: String,
      default: () => {
        return 'text'
      }
    },
    type: {
      type: String,
      default: () => {
        return 'text' // text, select, readonly
      }
    },
    selectOptions: {
      type: Array,
      default: () => {
        return [] // {name,value}
      }
    },
    selectOption: {
      type: Object,
      default: () => {
        return {
          name: 'name',
          value: ''
        }
      }
    },
    rightSideIcon: {
      type: String,
      default: () => {
        return ''
      }
    }
  },
  data() {
    return {
      isFocused: false,
      privateId: null,
    }
  },
  computed: {
    hasOutline() {
      return this.isFocused || !!this.value
    }
  },
  created() {
    privateId++
    this.privateId = privateId
  },
  methods: {
    onEnter( event ) {
      this.$emit( 'enter', event )
    },
    onFocus( event ) {
      this.isFocused = true
      this.$emit( 'focus', event )
    },
    onInput( $event ) {
      let value = _.get( $event, 'target.value' ) || ''
      if( this.inputType === 'number' && !!this.maxLength && ((_.get( value, 'length' ) || 0) > this.maxLength) ) {
        value = value.substring( 0, this.maxLength )
        $event.target.value = value
      }
      this.$emit( 'input', value )
    }
  }
}
</script>

<style scoped lang="scss">
.normal-input-wrapper {
  width: 100%;
  height: 100px;
  display: flex;
  align-items: center;
  border-radius: 4px;
  background-color: #ffffff;
  color: #cccccc;
  font-size: 16px;
  overflow: hidden;
  border: solid 1px #bdbfcd;

  &.has-outline {
    border: solid 1px #333333;
    color: #333333;

    .icon-area {
      .s-icon {
        color: #333333;
      }
    }
  }

  &.disabled {
    border: solid 1px #bdbfcd;

    .type-text-wrapper {
      background-color: #eeeeee;
      color: #bdbfcd;

      * {
        color: inherit;
      }

      .pure-input {
        color: inherit;

        &::placeholder {
          color: inherit;
        }
      }

      .icon-area {
        color: inherit;

        .s-icon {
          color: inherit;
        }
      }

      .suffix-area {
        color: inherit;
      }
    }

    .type-select-wrapper {
      background-color: #eeeeee;
      color: #bdbfcd;

      * {
        color: inherit;
      }

      .icon-area {
        color: inherit;

        .s-icon {
          color: inherit;
        }
      }

      .suffix-area {
        color: inherit;
      }
    }

    .type-readonly-wrapper {
      background-color: #eeeeee;
      color: #bdbfcd;

      * {
        color: inherit;
      }

      .icon-area {
        color: inherit;

        .s-icon {
          color: inherit;
        }
      }

      .suffix-area {
        color: inherit;
      }
    }
  }

  .type-text-wrapper {
    display: flex;
    flex: 1;
    height: 100%;
    padding: 0 9px;

    &.button {
      padding: 0 0 0 9px;
    }

    .pure-input {
      flex: 1;
      width: 100%;
      height: 100%;
      font-size: inherit;
      font-family: NanumBarunGothic;
      font-weight: normal;
      border: none;
      outline: none;
      background-color: transparent;

      &::placeholder {
        color: #bdbfcd;
      }

      &.number-type-arrow-hidden::-webkit-inner-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }

      &.number-type-arrow-hidden::-webkit-outer-spin-button {
        -webkit-appearance: none;
        margin: 0;
      }

      &.number-type-arrow-hidden[type=number] {
        -moz-appearance: textfield;
      }
    }

    .suffix-area {
      display: flex;
      align-items: center;
      color: inherit;
    }
  }

  .type-select-wrapper {
    display: flex;
    flex: 1;
    height: 100%;
    position: relative;

    .pure-select {
      background-color: transparent;
      font-size: inherit;
      font-family: NanumBarunGothic;
      font-weight: normal;
      padding: 0 9px;
      flex: 1;
      height: 100%;
      border: none;
      outline: none;
      -webkit-appearance: none;
      -moz-appearance: none;
      text-indent: 1px;
      text-overflow: '';

      .select-option {
        height: 48px;
        line-height: 48px;

        border: none;
        outline: none;
      }
    }

    .icon-area {
      position: absolute;
      pointer-events: none;
      right: 9px;
    }

    .select-placeholder {
      pointer-events: none;
      font-size: inherit;
      font-family: NanumBarunGothic;
      font-weight: normal;
      height: 100%;
      justify-content: center;
      align-items: center;
      display: flex;
      font-size: inherit;
      position: absolute;
      color: #bdbfcd;
      left: 9px;
    }
  }

  .type-readonly-wrapper {
    display: flex;
    flex: 1;
    height: 100%;
    position: relative;

    .readonly-value-text {
      display: flex;
      align-items: center;
      font-size: inherit;
      font-family: NanumBarunGothic;
      font-weight: normal;
      padding: 0 9px;
      flex: 1;
      height: 100%;
    }

    .icon-area {
      position: absolute;
      pointer-events: none;
      right: 9px;
    }

    .readonly-placeholder {
      pointer-events: none;
      font-size: inherit;
      font-family: NanumBarunGothic;
      font-weight: normal;
      height: 100%;
      justify-content: center;
      align-items: center;
      display: flex;
      font-size: inherit;
      position: absolute;
      color: #bdbfcd;
      left: 9px;
    }
  }

  .icon-area {
    flex: 0 0;
    height: 100%;
    justify-content: center;
    align-items: center;
    display: flex;

    &.button {
      position: relative;
      flex: 0 0 50px;
      border-radius: 10%;
    }

    .s-icon {
      font-size: 30px;
      color: #bdbfcd;
    }
  }
}
</style>
