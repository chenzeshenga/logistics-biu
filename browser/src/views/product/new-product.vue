<template>
    <div class="login-container">
        <el-upload
            action="http://localhost:8888/api/v1/product/img"
            with-credentials
            multiple
            list-type="picture-card"
            :on-preview="handlePictureCardPreview"
            :on-remove="handleRemove"
            :data="extra">
            <i class="el-icon-plus"></i>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
            <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
    </div>
</template>

<script>
    import request from '@/utils/request'

    export default {
        name: "new-product",
        data() {
            return {
                dialogImageUrl: '',
                dialogVisible: false,
                extra: {
                    tmp: "xxx"
                }
            };
        },
        created() {
            this.withEditor();
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
            }
        }
    }
</script>
