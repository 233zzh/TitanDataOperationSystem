/**
 * 把小数转化为百分数
 * @param value 小数
 * @param pos 保留小数点后位数
 * @return {number}
 */
function decimalToPercent(value, pos) {
    return Math.round(value*Math.pow(10,pos+2))/Math.pow(10,pos);
}

/**
 * 深拷贝
 * @param object 输入元素
 * @return {any} 副本
 */
function copy(object) {
    return JSON.parse(JSON.stringify(object))
}

/**
 * 将二维数组按照某一位排序
 * @param list 二维数组
 * @param index 排序位置
 * @param n 前N个
 * @return topN结果
 */
function topN(list, index, n) {
    var xAxis = [];
    var yAxis = [];

    // 排序
    list.sort(function (a, b) {
        return a[index]<b[index]?1:-1;
    });
    // 获取数据
    list.slice(0, n).forEach(
        function (item) {
            xAxis.push(item[0]);
            yAxis.push(item[index]);
        }
    );
    // 返回结果
    return {
        x: xAxis,
        y: yAxis
    }
}

/**
 * 再列表中指定列后增加百分比列（一列中每个元素占这列和的百分比)
 * @param list
 * @param indexes
 */
function toPercent(list, ...indexes) {
    var sum =  list.reduce(function (previous, current) {
        var res = [];
        indexes.forEach(function (item,index) {
            res.push(previous[index]+current[item]);
        });
        return res;
    },new Array(indexes.length).fill(0));

    list.map(function (value) {
        indexes.forEach(function (item,index) {
            value.splice(item+index+1, 0 ,decimalToPercent(value[item+index]/sum[index],2));
        });
        return value;
    });
}

/**
 *
 * @param list
 * @param column
 * @param columnIndex
 */
function addColumn(list, column, columnIndex) {
    list.map(function (value, index) {
        value.splice(columnIndex, 0, column[index]);
        return value;
    });
}

/**
 * 再二维数组中指定位置增加一列，返回将这列之后所有列的元素转化为占这对应行元素百分数的数组
 * @param list 二维数组
 * @param column 列
 * @param start 插入位置
 */
function addSumAndPercent(list, column, start) {
    // 增加列
    addColumn(list, column, start);
    // 创建百分比矩阵
    var res = [];
    list.forEach(function (item) {
        var cur = copy(item);
        for (let i = start+1; i < cur.length; i++) {
            if (cur[i]===undefined||cur[i]===null) break;
            cur[i] = decimalToPercent(cur[i]/cur[start],2);
        }
        res.push(cur);
    });
    return res;
}

/**
 * 获取一列
 * @param list 二维数组
 * @param columnIndex 列号
 */
function getColumn(list, columnIndex) {
    var res = [];
    list.forEach(function (value) {
        if (value[columnIndex]===undefined||value[columnIndex]===null) res.push(0);
        else res.push(value[columnIndex]);
    });
    return res;
}

/**
 * 根据留存率渲染单元格颜色
 * @param td
 * @param cellData
 * @param rowData
 * @param row
 * @param col
 */
function cellColor(td, cellData, rowData, row, col) {
    if (cellData===null||cellData===undefined) return;
    if ( cellData === 0  )  $(td).css('background-color', '#ffffff');
    else if (cellData <= 20) $(td).css('background-color', '#e6f7ff');
    else if (cellData <= 40) $(td).css('background-color', '#bae7ff');
    else if (cellData <= 60) $(td).css('background-color', '#91d5ff');
    else $(td).css('background-color', '#69c0ff');
}

/**
 * 指定起止列号，添加颜色渲染
 * @param start
 * @param end
 * @return {[]}
 */
function tableColDefColor(start, end) {
    var res = [];
    for (let i=start ; i<end; i++) {
        res.push({
            "targets": i,
            "createdCell": cellColor,
            "render":cellPercent
        })
    }
    return res;
}
// 单元格添加百分号
function cellPercent(data, type, row, meta) {
    if (data===null||data===undefined) return;
    return data + '%';
}

/**
 * 按列显示百分号
 * @param columns
 * @return {[]}
 */
function tableColDefPer(...columns){
    var res = [];
    for (i of columns) {
        res.push({
            "targets": i,
            "render":cellPercent
        })
    }
    return res
}


/**
 * 把多个列表转成一个二维数组
 * @param list 待转换的多个列表
 */
function listsToMatrix(...list) {
    var len = list[0].length;
    var allData = [];
    for(var i = 0; i < len; i++) {
        allData[i] = [];
        for(var j = 0; j < list.length; j++) {
            allData[i][j] = list[j][i];
        }
    }

    console.log(allData);
    return allData;
}