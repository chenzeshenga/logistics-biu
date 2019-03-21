<template>
    <div class="login-container">
        <div class="app-container">
            <el-form ref="form" :rules="checkRules" :model="form" label-width="120px">
                <el-form-item label="产品信息">
                    <el-col :span="12">
                        <el-form-item label="sku" prop="sku">
                            <el-input v-model="form.sku" v-bind:disabled="onUpdate" placeholder="请输入或者扫描产品sku">
                                <el-button slot="append" v-bind:disabled="onUpdate" @click="getDySku">获取唯一sku</el-button>
                            </el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="产品名称" prop="productName">
                            <el-input v-model="form.productName" placeholder="请输入产品名称"/>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="产品类型" prop="category">
                            <el-select v-model="form.category" placeholder="请选择产品类型">
                                <el-option value="1" label="小物"/>
                                <el-option value="2" label="服装"/>
                                <el-option value="3" label="户外运动"/>
                                <el-option value="4" label="电子产品"/>
                                <el-option value="5" label="家居用品"/>
                                <el-option value="6" label="玩具"/>
                                <el-option value="7" label="大件"/>
                                <el-option value="8" label="其他"/>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="产品颜色">
                            <el-input v-model="form.color" placeholder="请输入产品颜色"/>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="12">
                        <el-form-item label="产品价格(JPY)" prop="price">
                            <el-input-number v-model="form.price"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="产品尺寸">
                            <el-input v-model="form.size" placeholder="请输入产品尺寸"></el-input>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="产品尺寸">
                    <el-col :span="8">
                        <el-form-item label="长(cm)">
                            <el-input-number v-model="form.length"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="宽(cm)">
                            <el-input-number v-model="form.width"></el-input-number>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="高(cm)">
                            <el-input-number v-model="form.height"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item>
                    <el-col :span="8">
                        <el-form-item label="重量(kg)">
                            <el-input-number v-model="form.weight"></el-input-number>
                        </el-form-item>
                    </el-col>
                </el-form-item>
                <el-form-item label="产品图片">
                    <el-upload action="http://localhost:8888/api/v1/product/img" with-credentials multiple list-type="picture"
                               :file-list="fileList" :on-preview="handlePictureCardPreview" :on-remove="handleRemove" :data="form"
                               ref="upload" :on-error="handleError" :limit="3">
                        <el-button slot="trigger" size="small" type="primary">选取文件</el-button>
                        <el-button style="margin-left: 10px;" size="small" type="success" @click="submitUpload">上传到服务器</el-button>
                        <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过2MB</div>
                    </el-upload>
                    <el-dialog :visible.sync="dialogVisible">
                        <img width="100%" :src="dialogImageUrl" alt="">
                    </el-dialog>
                </el-form-item>
                <el-form-item>
                    <el-col :offset="20">
                        <el-button type="primary" @click="submitForm('form')">立即创建</el-button>
                        <el-button @click="resetForm('form')">重置</el-button>
                    </el-col>
                </el-form-item>
            </el-form>

        </div>
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "new-product",
        data() {
            return {
                onUpdate: false,
                onCreate: true,
                dialogImageUrl: '',
                dialogVisible: false,
                form: {
                    sku: '',
                    productName: '',
                    category: '8',
                    color: '',
                    price: 0,
                    size: '',
                    length: 0,
                    width: 0,
                    height: 0,
                    weight: 0,
                },
                checkRules: {
                    sku: [
                        {required: true, message: "请输入sku", trigger: 'blur'},
                        {min: 3, max: 20, message: "sku长度在3-20之间", trigger: 'blur'},
                    ],
                    productName: [
                        {required: true, message: "请输入商品名称", trigger: 'change'}
                    ],
                    category: [
                        {required: true, message: "请选择商品类型", trigger: 'change'}
                    ],
                    price: [
                        {required: true, message: "请输入商品价格", trigger: 'change'}
                    ]
                },
                fileList: []
            };
        },
        created() {
            // this.withEditor();
        },
        methods: {
            handleRemove(file, fileList) {
                console.log(file, fileList);
            },
            handlePictureCardPreview(file) {
                this.dialogImageUrl = file.url;
                this.dialogVisible = true;
            },
            withEditor() {
                request({
                    url: "/product/listWithImg",
                    method: "get",
                }).then(res => {
                    console.log(res);
                })
            },
            getDySku() {
                request({
                    url: "/generate/sku",
                    method: "get"
                }).then(res => {
                    this.form.sku = res.data.data;
                })
            },
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        request({
                            url: "/product/add",
                            method: "post",
                            data: this.form
                        }).then(res => {
                            console.log(res);
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            formCheck() {
                this.$refs['form'].validate((valid) => {
                    if (valid) {
                        return true;
                    } else {
                        this.$message.error("请填写必填项");
                        return false;
                    }
                });
            },
            beforeAvatarUpload(file) {
                const isFormReady = this.formCheck();
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M && isFormReady;
            },
            submitUpload() {
                this.$refs.upload.submit();
            },
            handleError(err, file, fileList) {
                this.$message.error(JSON.parse(err.message)["message"]);
            }
        }
    }
</script>
