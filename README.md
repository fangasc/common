<<<<<<< e1241bb1bdddff2d39a0681806618d310dcc9a58
# common
日常使用公共
=======

# easyreader 个人业余写的读写，简化日常文件读写操作
# 使用方式
EasyReader.reader(new FileInputStream("a.xlsx"), FileTypeEnum.XLSX, (line, context) -> {
			System.out.println(line);
});

# 格式化
EasyReader.reader(new FileInputStream("a.xlsx"), FileTypeEnum.XLSX, (line, context) -> {
	System.out.println(line);
}, (line, context) -> {
	//处理
});

# 扩展 
读取其他文件，继承AbstractReader 实现read方法。FileTypeEnum中没有的类型 新增一下，desc为新建类名称，系统自动反射生成解析类实例。
>>>>>>> aab040c19118d4cd333bf6afeee14cdc12b63b4a
