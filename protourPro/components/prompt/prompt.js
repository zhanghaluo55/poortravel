import Component from '../component'

export default {
	/**
	 * 默认参数
	 */
	setDefaults() {
		return {
			className: undefined, 
      icon:"icon-empty",
			title: undefined, 
			text: undefined, 
			buttons: [], 
			buttonClicked() {}, 
		}
	},
	/**
	 * 显示提示信息组件
	 * @param {String} id   唯一标识
	 * @param {Object} opts 配置项
	 * @param {String} opts.className 自定义类名
	 * @param {String} opts.icon 图标
	 * @param {String} opts.title 标题
	 * @param {String} opts.text 文本
	 * @param {Array} opts.buttons 按钮
	 * @param {Function} opts.buttonClicked 按钮点击事件
	 */
	init(id, opts = {}) {
		const options = Object.assign({}, this.setDefaults(), opts)

    	// 实例化组件
    	const component = new Component({
    		scope: `$wux.prompt.${id}`, 
    		data: options, 
    		methods: {
    			/**
    			 * 隐藏
    			 */
				hide() {
					this.setHidden()
				},
				/**
				 * 显示
				 */
				show() {
					this.setVisible()
				},
				/**
				 * 点击按钮触发事件
				 */
				buttonClicked(e) {
					const index = e.currentTarget.dataset.index
	    			typeof options.buttonClicked === `function` && options.buttonClicked(index, options.buttons[index])
				},
			},
    	})

		return {
			show: component.show, 
			hide: component.hide, 
		}
	},
}