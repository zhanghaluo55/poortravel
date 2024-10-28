let info;

function getSystemInfo() {
  if (info) return info;
  info = wx.getSystemInfoSync();
  return info;
}

export function isIos() {
  const sys = getSystemInfo();
  return /iphone|ios/i.test(sys.platform);
}

/**
 * new Date 区分平台
 * @param {number} year
 * @param {number} month
 * @param {number} day
 */
function newDate(year, month, day) {
  let cur = `${year}-${month}-${day}`;
  if (isIos()) {
    cur = `${year}/${month}/${day}`;
  }
  return new Date(cur);
}

/**
 * 上滑
 * @param {object} e 事件对象
 * @returns {boolean} 布尔值
 */
export function isUpSlide(e) {
  const {
    startX,
    startY
  } = this.data.gesture;
  if (this.slideLock) {
    const t = e.touches[0];
    const deltaX = t.clientX - startX;
    const deltaY = t.clientY - startY;
    if (deltaY < -60 && deltaX < 20 && deltaX > -20) {
      this.slideLock = false;
      return true;
    } else {
      return false;
    }
  }
}
/**
 * 下滑
 * @param {object} e 事件对象
 * @returns {boolean} 布尔值
 */
export function isDownSlide(e) {
  const {
    startX,
    startY
  } = this.data.gesture;
  if (this.slideLock) {
    const t = e.touches[0];
    const deltaX = t.clientX - startX;
    const deltaY = t.clientY - startY;
    if (deltaY > 60 && deltaX < 20 && deltaX > -20) {
      this.slideLock = false;
      return true;
    } else {
      return false;
    }
  }
}
/**
 * 左滑
 * @param {object} e 事件对象
 * @returns {boolean} 布尔值
 */
export function isLeftSlide(e) {
  const {
    startX,
    startY
  } = this.data.gesture;
  if (this.slideLock) {
    const t = e.touches[0];
    const deltaX = t.clientX - startX;
    const deltaY = t.clientY - startY;
    if (deltaX < -60 && deltaY < 20 && deltaY > -20) {
      this.slideLock = false;
      return true;
    } else {
      return false;
    }
  }
}
/**
 * 右滑
 * @param {object} e 事件对象
 * @returns {boolean} 布尔值
 */
export function isRightSlide(e) {
  const {
    startX,
    startY
  } = this.data.gesture;
  if (this.slideLock) {
    const t = e.touches[0];
    const deltaX = t.clientX - startX;
    const deltaY = t.clientY - startY;

    if (deltaX > 60 && deltaY < 20 && deltaY > -20) {
      this.slideLock = false;
      return true;
    } else {
      return false;
    }
  }
}
/**
 *  todo 数组去重
 * @param {array} array todo 数组
 */
function uniqueTodoLabels(array = []) {
  let uniqueObject = {};
  let uniqueArray = [];
  array.forEach(item => {
    uniqueObject[`${item.year}-${item.month}-${item.day}`] = item;
  });
  for (let i in uniqueObject) {
    uniqueArray.push(uniqueObject[i]);
  }
  return uniqueArray;
}

const conf = {
  /**
   * 计算指定月份共多少天
   * @param {number} year 年份
   * @param {number} month  月份
   */
  getThisMonthDays(year, month) {
    return new Date(year, month, 0).getDate();
  },
  /**
   * 计算指定月份第一天星期几
   * @param {number} year 年份
   * @param {number} month  月份
   */
  getFirstDayOfWeek(year, month) {
    return new Date(Date.UTC(year, month - 1, 1)).getDay();
  },
  /**
   * 计算指定日期星期几
   * @param {number} year 年份
   * @param {number} month  月份
   * @param {number} date 日期
   */
  getDayOfWeek(year, month, date) {
    return new Date(Date.UTC(year, month - 1, date)).getDay();
  },
  /**
   * 计算当前月份前后两月应占的格子
   * @param {number} year 年份
   * @param {number} month 月份
   */
  calculateEmptyGrids(year, month) {
    conf.calculatePrevMonthGrids.call(this, year, month);
    conf.calculateNextMonthGrids.call(this, year, month);
  },
  /**
   * 计算上月应占的格子
   * @param {number} year 年份
   * @param {number} month 月份
   */
  calculatePrevMonthGrids(year, month) {
    const prevMonthDays = conf.getThisMonthDays(year, month - 1);
    const firstDayOfWeek = conf.getFirstDayOfWeek(year, month);
    let empytGrids = [];
    if (firstDayOfWeek > 0) {
      const len = prevMonthDays - firstDayOfWeek;
      for (let i = prevMonthDays; i > len; i--) {
        empytGrids.push(i);
      }
      this.setData({
        'calendar.empytGrids': empytGrids.reverse(),
      });
    } else {
      this.setData({
        'calendar.empytGrids': null,
      });
    }
  },
  /**
   * 计算下月应占的格子
   * @param {number} year 年份
   * @param {number} month  月份
   */
  calculateNextMonthGrids(year, month) {
    const thisMonthDays = conf.getThisMonthDays(year, month);
    const lastDayWeek = newDate(year, month, thisMonthDays).getDay();
    let lastEmptyGrids = [];
    if (+lastDayWeek !== 6) {
      const len = 7 - (lastDayWeek + 1);
      for (let i = 1; i <= len; i++) {
        lastEmptyGrids.push(i);
      }
      this.setData({
        'calendar.lastEmptyGrids': lastEmptyGrids,
      });
    } else {
      this.setData({
        'calendar.lastEmptyGrids': null,
      });
    }
  },
  /**
   * 设置日历面板数据
   * @param {number} year 年份
   * @param {number} month  月份
   */
  calculateDays(year, month, curDate) {
    let days = [];
    let data = new Date();
    const { todayTimestamp } = this.data.calendar;
    const thisMonthDays = conf.getThisMonthDays(year, month);
    const selectedDay = this.data.calendar.selectedDay || [{
      day: curDate,
      choosed: true,
      year,
      month,
    }];
    for (let i = 1; i <= thisMonthDays; i++) {
      days.push({
        day: i,
        choosed: false,
        year,
        month,
      });
    }
    console.log(selectedDay);
    const selectedDayCol = selectedDay.map(d => `${d.year}-${d.month}-${d.day}`);
    const toDayCol = [`${data.getFullYear()}-${data.getMonth() + 1}-${data.getDate()}`];
    days.map(item => {
      const cur = `${item.year}-${item.month}-${item.day}`;
      if (toDayCol.indexOf(cur) !== -1) item.today = true;
      if (selectedDayCol.indexOf(cur) !== -1) item.choosed = true;
      const timestamp = newDate(item.year, item.month, item.day).getTime();
      const week = newDate(item.year, item.month, item.day).getDay();
      if (this.config.disablePastDay && (timestamp - todayTimestamp < 0)) item.disable = true;
      if (this.config.disableFutureDay && (timestamp - todayTimestamp > 2 * 365 * 24 * 60 * 60 * 1000)) item.disable = true;
      if (this.config.canWeek && (week != this.config.week)) item.disable = true;
    });
    const tmp = {
      'calendar.days': days
    };
    if (curDate) {
      tmp['calendar.selectedDay'] = selectedDay;
    }
    this.setData(tmp);
  },
  /**
   * 选择上一月
   */
  choosePrevMonth() {
    const {
      curYear,
      curMonth
    } = this.data.calendar;
    let newYear = curYear;
    let newMonth = curMonth - 1;
    if (newMonth < 1) {
      newYear = curYear - 1;
      newMonth = 12;
    }
    conf.renderCalendar.call(this, newYear, newMonth);
    this.setData({
      'calendar.curYear': newYear,
      'calendar.curMonth': newMonth,
    });
  },
  /**
   * 选择下一月
   */
  chooseNextMonth() {
    const curYear = this.data.calendar.curYear;
    const curMonth = this.data.calendar.curMonth;
    let newMonth = curMonth + 1;
    let newYear = curYear;
    if (newMonth > 12) {
      newYear = curYear + 1;
      newMonth = 1;
    }
    conf.renderCalendar.call(this, newYear, newMonth);
    this.setData({
      'calendar.curYear': newYear,
      'calendar.curMonth': newMonth
    });
  },
  /**
   * 选择具体日期
   * @param {!object} e  事件对象
   */
  tapDayItem(e) {
    const {
      idx,
      disable
    } = e.currentTarget.dataset;
    if (disable) return;
    let currentSelected = {}; // 当前选中日期
    let {
      days,
      selectedDay: selectedDays
    } = this.data.calendar || []; // 所有选中日期
    const config = this.config;
    const {
      multi,
      afterTapDay,
      onTapDay
    } = config;
    const opts = {
      e,
      idx,
      onTapDay,
      currentSelected,
      selectedDays,
      days: days.slice(),
    };
    if (multi) {
      conf.whenMulitSelect.call(this, opts);
    } else {
      conf.whenSingleSelect.call(this, opts);
    }
    if (afterTapDay && typeof afterTapDay === 'function') {
      if (!multi) {
        config.afterTapDay(currentSelected);
      } else {
        config.afterTapDay(currentSelected, selectedDays);
      }
    };
  },
  /**
   * 多选
   * @param {object} opts
   */
  whenMulitSelect(opts = {}) {
    let {
      currentSelected,
      selectedDays
    } = opts;
    const {
      days,
      idx,
      onTapDay,
      e
    } = opts;
    days[idx].choosed = !days[idx].choosed;
    if (!days[idx].choosed) {
      days[idx].cancel = true; // 点击事件是否是取消日期选择
      currentSelected = days[idx];
      selectedDays = selectedDays.filter(item => item.day !== days[idx].day);
    } else {
      currentSelected = days[idx];
      selectedDays.push(currentSelected);
    }
    if (onTapDay && typeof onTapDay === 'function') {
      this.config.onTapDay(currentSelected, e);
      return;
    };
    this.setData({
      'calendar.days': days,
      'calendar.selectedDay': selectedDays,
    });
  },
  /**
   * 多选
   * @param {object} opts
   */
  whenSingleSelect(opts = {}) {
    let {
      currentSelected,
      selectedDays
    } = opts;
    let shouldMarkerTodoDay = [];
    const {
      days,
      idx,
      onTapDay,
      e
    } = opts;
    const {
      month: sMonth,
      year: sYear
    } = selectedDays[0];
    const {
      month: dMonth,
      year: dYear
    } = days[0];
    const {
      calendar = {}
    } = this.data;
    if (sMonth === dMonth && sYear === dYear) {
      if (this.weekMode) {
        days.map((item, idx) => {
          if (item.day === selectedDays[0].day) days[idx].choosed = false;
        });
      } else {
        days[selectedDays[0].day - 1].choosed = false;
      }
    }
    if (calendar.todoLabels) {
      // 过滤所有待办日期中当月有待办事项的日期
      shouldMarkerTodoDay = calendar.todoLabels.filter(item => {
        return item.year === dYear && item.month === dMonth;
      });
    }
    shouldMarkerTodoDay.forEach(item => {
      // hasTodo 是否有待办事项
      if (this.weekMode) {
        days.map((_item, idx) => {
          if (_item.day === item.day) {
            days[idx].hasTodo = true;
            if (selectedDays[0].day === item.day) days[idx].showTodoLabel = true;
          }
        });
      } else {
        days[item.day - 1].hasTodo = true;
        // showTodoLabel 是否显示待办标记
        if (selectedDays[0].day === item.day) days[selectedDays[0].day - 1].showTodoLabel = true;
      }
    });
    if (days[idx].showTodoLabel) days[idx].showTodoLabel = false;
    days[idx].choosed = true;
    currentSelected = days[idx];
    if (onTapDay && typeof onTapDay === 'function') {
      this.config.onTapDay(currentSelected, e);
      return;
    };
    this.setData({
      'calendar.days': days,
      'calendar.selectedDay': [currentSelected],
    });
  },
  /**
   * 设置代办事项标志
   * @param {object} options 代办事项配置
   */
  setTodoLabels(options = {}) {
    const {
      calendar
    } = this.data;
    if (!calendar || !calendar.days) {
      console.error('请等待日历初始化完成后再调用该方法');
      return;
    }
    const days = calendar.days.slice();
    const {
      year,
      month
    } = days[0];
    const {
      days: todoDays = [],
      pos = 'bottom',
      dotColor = ''
    } = options;
    const {
      todoLabels = [], todoLabelPos, todoLabelColor
    } = calendar;
    const shouldMarkerTodoDay = todoDays.filter(item => +item.year === year && +item.month === month);
    if ((!shouldMarkerTodoDay || !shouldMarkerTodoDay.length) && !todoLabels.length) return;
    let temp = [];
    let currentMonthTodoLabels = todoLabels.filter(item => +item.year === year && +item.month === month);
    shouldMarkerTodoDay.concat(currentMonthTodoLabels).forEach((item) => {
      temp.push(days[item.day - 1]);
      days[item.day - 1].showTodoLabel = !days[item.day - 1].choosed;
    });
    const o = {
      'calendar.days': days,
      'calendar.todoLabels': uniqueTodoLabels(todoDays.concat(todoLabels)),
    };
    if (pos && pos !== todoLabelPos) o['calendar.todoLabelPos'] = pos;
    if (dotColor && dotColor !== todoLabelColor) o['calendar.todoLabelColor'] = dotColor;
    this.setData(o);
  },
  /**
   * 筛选待办事项
   * @param {array} todos 需要删除待办标记的日期
   */
  filterTodos(todos) {
    const {
      todoLabels
    } = this.data.calendar;
    const deleteTodo = todos.map(item => `${item.year}-${item.month}-${item.day}`);
    return todoLabels.filter(item => deleteTodo.indexOf(`${item.year}-${item.month}-${item.day}`) === -1);
  },
  /**
   *  删除指定日期的待办标识
   * @param {array} todos  需要删除待办标记的日期
   */
  deleteTodoLabels(todos) {
    if (!(todos instanceof Array)) return;
    if (!todos.length) return;
    const todoLabels = conf.filterTodos.call(this, todos);
    const {
      days,
      curYear,
      curMonth
    } = this.data.calendar;
    const currentMonthTodoLabels = todoLabels.filter(item => curYear === item.year && curMonth === item.month);
    days.map(item => {
      item.showTodoLabel = false;
    });
    currentMonthTodoLabels.forEach(item => {
      days[item.day - 1].showTodoLabel = !days[item.day - 1].choosed;
    });
    this.setData({
      'calendar.days': days,
      'calendar.todoLabels': todoLabels,
    });
  },
  /**
   * 清空所有日期的待办标识
   */
  clearTodoLabels() {
    const {
      days = []
    } = this.data.calendar;
    const _days = [].concat(days);
    _days.map(item => {
      item.showTodoLabel = false;
    });
    this.setData({
      'calendar.days': _days,
      'calendar.todoLabels': [],
    });
  },
  /**
   * 跳转至今天
   */
  jumpToToday() {
    const date = new Date();
    const curYear = date.getFullYear();
    const curMonth = date.getMonth() + 1;
    const curDate = date.getDate();
    const timestamp = newDate(curYear, curMonth, curDate).getTime();
    cons
    this.setData({
      'calendar.curYear': curYear,
      'calendar.curMonth': curMonth,
      'calendar.selectedDay': [{
        day: curDate,
        choosed: true,
        year: curYear,
        month: curMonth,
      }],
      'calendar.todayTimestamp': timestamp,
    });
    conf.renderCalendar.call(this, curYear, curMonth, curDate);
  },
  /**
   * 跳转至制定日期
   */
  jumpToOneday(year, month, day) {
    const date = new Date();
    const curYear = date.getFullYear();
    const curMonth = date.getMonth() + 1;
    const curDate = date.getDate();
    const timestamp = newDate(curYear, curMonth, curDate).getTime();
    this.setData({
      'calendar.curYear': year || curYear,
      'calendar.curMonth': month || curMonth,
      'calendar.selectedDay': [{
        day: day,
        choosed: true,
        year: year,
        month: month,
      }],
      'calendar.todayTimestamp': timestamp,
    });
    conf.renderCalendar.call(this, year || curYear, month || curMonth, day || curDate);
  },
  renderCalendar(curYear, curMonth, curDate) {
    conf.calculateEmptyGrids.call(this, curYear, curMonth);
    conf.calculateDays.call(this, curYear, curMonth, curDate);
    const {
      todoLabels
    } = this.data.calendar || {};
    const {
      afterCalendarRender
    } = this.config;
    if (todoLabels && todoLabels instanceof Array) conf.setTodoLabels.call(this);
    if (afterCalendarRender && typeof afterCalendarRender === 'function' && !this.firstRender) {
      afterCalendarRender();
      this.firstRender = true;
    }
  },
  calendarTouchstart(e) {
    const t = e.touches[0];
    const startX = t.clientX;
    const startY = t.clientY;
    this.slideLock = true; // 滑动事件加锁
    this.setData({
      'gesture.startX': startX,
      'gesture.startY': startY
    });
  },
  calendarTouchmove(e) {
    if (isLeftSlide.call(this, e)) {
      conf.chooseNextMonth.call(this);
    }
    if (isRightSlide.call(this, e)) {
      conf.choosePrevMonth.call(this);
    }
  },
  /**
   * 计算当前选中日期所在周，并重新渲染日历
   * @param {object} currentDay 当前选择日期
   */
  selectedDayWeekAllDays(currentDay) {
    const {
      days
    } = this.data.calendar;
    const {
      year,
      month,
      day
    } = currentDay;
    const firstWeekDays = conf.firstWeek.call(this, year, month);
    const lastWeekDays = conf.lastWeek.call(this, year, month, day);
    if (firstWeekDays.find(item => item.day === day)) {
      conf.calculatePrevMonthGrids.call(this, year, month);
      this.setData({
        'calendar.days': firstWeekDays,
        'calendar.lastEmptyGrids': [],
      });
    } else if (lastWeekDays.find(item => item.day === day)) {
      conf.calculateNextMonthGrids.call(this, year, month);
      this.setData({
        'calendar.days': lastWeekDays,
        'calendar.empytGrids': [],
      });
    } else {
      const week = conf.getDayOfWeek(year, month, day);
      const range = [day - week, day + (6 - week)];
      const daysCut = days.slice(range[0] - 1, range[1]);
      this.setData({
        'calendar.days': daysCut,
        'calendar.lastEmptyGrids': [],
        'calendar.empytGrids': [],
      });
    }
  },
  /**
   * 当月第一周所有日期范围
   * @param {number} year
   * @param {number} month
   * @param {number} day
   */
  firstWeek(year, month) {
    const firstDay = conf.getDayOfWeek(year, month, 1);
    const firstWeekDays = [1, 1 + (6 - firstDay)];
    const {
      days
    } = this.data.calendar;
    const daysCut = days.slice(firstWeekDays[0] - 1, firstWeekDays[1]);
    return daysCut;
  },
  /**
   * 当月最后一周所有日期范围
   * @param {number} year
   * @param {number} month
   * @param {number} day
   */
  lastWeek(year, month, day) {
    const lastDay = conf.getThisMonthDays(year, month);
    const lastDayWeek = conf.getDayOfWeek(year, month, lastDay);
    const lastWeekDays = [lastDay - lastDayWeek, lastDay];
    const {
      days
    } = this.data.calendar;
    const daysCut = days.slice(lastWeekDays[0] - 1, lastWeekDays[1]);
    return daysCut;
  },
  /**
   * 周、月视图切换
   * @param {string} view  视图 [week, month]
   */
  switchWeek(view) {
    if (this.config.multi) return console.error('多选模式不能切换周月视图');
    const {
      selectedDay = []
    } = this.data.calendar;
    if (!selectedDay.length) return;
    const currentDay = selectedDay[0];
    if (view === 'week') {
      if (this.weekMode) return;
      this.weekMode = true;
      conf.selectedDayWeekAllDays.call(this, currentDay);
    } else {
      this.weekMode = false;
      conf.renderCalendar.call(this, currentDay.year, currentDay.month, currentDay.curDate);
    }
  },
};

/**
 * 获取当前页面实例
 */
function _getCurrentPage() {
  const pages = getCurrentPages();
  const last = pages.length - 1;
  return pages[last];
}
/**
 * 绑定函数到当前页面实例上
 * @param {array} functionArray 函数数组
 */
function bindFunctionToPage(functionArray) {
  if (!functionArray || !functionArray.length) return;
  functionArray.forEach(item => {
    this[item] = conf[item].bind(this);
  });
}

/**
 * 获取已选择的日期
 */
export const getSelectedDay = () => {
  const self = _getCurrentPage();
  return self.data.calendar.selectedDay;
};
/**
 * 跳转至今天
 */
export const jumpToToday = () => {
  const self = _getCurrentPage();
  conf.jumpToToday.call(self);
};

/**
 * 设置代办事项日期标记
 * @param {object} todos  待办事项配置
 * @param {string} [todos.pos] 标记显示位置，默认值'bottom' ['bottom', 'top']
 * @param {string} [todos.dotColor] 标记点颜色，backgroundColor 支持的值都行
 * @param {object[]} todos.days 需要标记的所有日期，如：[{year: 2015, month: 5, day: 12}]，其中年月日字段必填
 */
export const setTodoLabels = (todos) => {
  const self = _getCurrentPage();
  conf.setTodoLabels.call(self, todos);
};

/**
 * 删除指定日期待办标记
 * @param {array} todos 需要删除的待办日期数组
 */
export const deleteTodoLabels = (todos) => {
  const self = _getCurrentPage();
  conf.deleteTodoLabels.call(self, todos);
};

/**
 * 清空所有待办标记
 */
export const clearTodoLabels = () => {
  const self = _getCurrentPage();
  conf.clearTodoLabels.call(self);
};

export const switchView = (view) => {
  const self = _getCurrentPage();
  conf.switchWeek.call(self, view);
};

export default (config = {}) => {
  const weeksCh = ['日', '一', '二', '三', '四', '五', '六'];
  const functionArray = ['tapDayItem', 'choosePrevMonth', 'chooseNextMonth', 'calendarTouchstart', 'calendarTouchmove'];
  const self = _getCurrentPage();
  self.config = config;
  self.setData({
    'calendar.weeksCh': weeksCh,
  });
  console.log("-----");
  console.log(self);
  conf.jumpToOneday.call(self, config.beginYear, config.beginMonth, config.beginDate);
  bindFunctionToPage.call(self, functionArray);
};