package com.abc.controller;

import com.abc.chenzeshenga.logistics.annotation.PermInfo;
import com.abc.chenzeshenga.logistics.util.SnowflakeIdWorker;
import com.abc.chenzeshenga.logistics.util.UserUtils;
import com.abc.constant.Root;
import com.abc.entity.SysRole;
import com.abc.entity.SysUser;
import com.abc.entity.SysUserRole;
import com.abc.service.SysRoleService;
import com.abc.service.SysUserRoleService;
import com.abc.service.SysUserService;
import com.abc.util.PageUtils;
import com.abc.vo.AuthVo;
import com.abc.vo.Json;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * created by CaiBaoHong at 2018/4/17 16:41<br>
 *
 * @deprecated
 */
@PermInfo(value = "系统用户模块", pval = "a:sys:接口")
@RestController
@RequestMapping("/sys_user")
@Deprecated
public class SysUserController {

    private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

    private SysUserService sysUserService;
    private SysRoleService sysRoleService;
    private SysUserRoleService sysUserRoleService;

    @Autowired
    public SysUserController(
            SysUserService sysUserService,
            SysRoleService sysRoleService,
            SysUserRoleService sysUserRoleService) {
        this.sysUserService = sysUserService;
        this.sysRoleService = sysRoleService;
        this.sysUserRoleService = sysUserRoleService;
    }

    @PermInfo("添加系统用户")
    @RequiresPermissions("a:sys:user:add")
    @PostMapping
    public Json add(@RequestBody String body) {
        String oper = "add sys user";
        log.info("{}, body: {}", oper, body);
        SysUser user = JSON.parseObject(body, SysUser.class);
        if (StringUtils.isEmpty(user.getUname())) {
            return Json.fail(oper, "用户帐号名不能为空");
        }
        if (StringUtils.isEmpty(user.getPwd())) {
            return Json.fail(oper, "密码不能为空");
        }
        SysUser userDB = sysUserService.selectUserByUserName(user.getUname());
        if (userDB != null) {
            return Json.fail(oper, "用户已注册");
        }
        // 密码加密
        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
        String salt = saltGen.nextBytes().toBase64();
        String hashedPwd = new Sha256Hash(user.getPwd(), salt, 1024).toBase64();
        // 保存新用户数据
        user.setPwd(hashedPwd);
        user.setSalt(salt);
        user.setCreated(new Date());
        user.setUid(SnowflakeIdWorker.generateStrId());
        int result = sysUserService.createUser(user);
        if (result > 0) {
            return Json.result(oper, true).data("uid", user.getUid()).data("created", user.getCreated());
        } else {
            return Json.fail(oper, "添加用户失败");
        }
    }

    @PermInfo("删除系统用户")
    @RequiresPermissions("a:sys:user:del")
    @DeleteMapping
    public Json delete(@RequestBody String body) {

        String oper = "delete user";
        log.info("{}, body: {}", oper, body);

        JSONObject jsonObj = JSON.parseObject(body);
        String uid = jsonObj.getString("uid");
        if (StringUtils.isEmpty(uid)) {
            return Json.fail(oper, "无法删除用户：参数为空（用户id）");
        }

        // 限制：不能删当前登录用户
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if (StringUtils.equals(uid, user.getUid())) {
            return Json.fail(oper, "系统限制：不能删除当前登录账号");
        }

        // 检查：不能删除管理员
        boolean containRoot = sysRoleService.checkUidContainRval(uid, Root.ROLE_VAL);
        if (containRoot) {
            return Json.fail(oper, "不能删除管理员用户");
        }

        boolean success = sysUserService.deleteById(uid);
        sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", uid));
        return Json.result(oper, success);
    }

    @PermInfo("更新系统用户的角色")
    @RequiresPermissions("a:sys:user:role:update")
    @PatchMapping("/role")
    public Json updateUserRole(@RequestBody String body) {

        String oper = "update user's roles";
        log.info("{}, body: {}", oper, body);

        JSONObject json = JSON.parseObject(body);
        final String uid = json.getString("uid");

        List<String> rids = json.getJSONArray("rids").toJavaList(String.class);

        // 检查：不能含有管理员角色
        boolean containRoot = sysRoleService.checkRidsContainRval(rids, Root.ROLE_VAL);
        if (containRoot) {
            return Json.fail(oper, "不能给非管理员用户赋予管理员角色");
        }

        // 删除：原来绑定的角色
        boolean deleteSucc =
                sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", uid));
        if (!deleteSucc) {
            return Json.fail(oper, "无法解除原来的用户-角色关系");
        }

        // 更新：绑定新的角色
        List<SysUserRole> list =
                rids.stream().map(roleId -> new SysUserRole(uid, roleId)).collect(Collectors.toList());

        if (!rids.isEmpty()) {
            boolean addSucc = sysUserRoleService.insertBatch(list);
            return Json.result(oper, addSucc);
        }
        return Json.succ(oper);
    }

    @PermInfo("查询所有系统用户")
    @RequiresPermissions("a:sys:user:list")
    @PostMapping("/query")
    public Json query(@RequestBody String body) {
        String oper = "query user";
        log.info("{}, body: {}", oper, body);
        JSONObject json = JSON.parseObject(body);
        String nick = json.getString("nick");
        Page<SysUser> page = sysUserService.queryUserIncludeRoles(PageUtils.getPageParam(json), nick);
        return Json.succ(oper).data("page", page);
    }

    @PermInfo("查询所有系统用户形成下拉菜单")
    @RequiresPermissions("a:sys:user:list:option")
    @PostMapping("/query4Option")
    public Json query4Option(@RequestBody String body) {
        String oper = "query user for option";
        JSONObject json = JSON.parseObject(body);
        String nick = json.getString("nick");
        Subject subject = SecurityUtils.getSubject();
        SysUser user = (SysUser) subject.getPrincipal();
        Set<AuthVo> authVos = user.getRoles();
        AtomicBoolean queryAll = new AtomicBoolean(false);
        authVos.forEach(
                authVo -> {
                    if ("root".equals(authVo.getVal()) || "operator".equals(authVo.getVal())) {
                        queryAll.set(true);
                    }
                });
        if (!queryAll.get()) {
            Page<SysUser> sysUserPage = new Page<>();
            List<SysUser> sysUserList = new ArrayList<>();
            sysUserList.add(user);
            sysUserPage.setRecords(sysUserList);
            return Json.succ(oper).data("page", sysUserPage);
        }
        Page<SysUser> page;
        if (queryAll.get()) {
            page = sysUserService.queryUserIncludeRoles(PageUtils.getPageParam(json), nick);
        } else {
            page =
                    sysUserService.queryUserIncludeRoles4Option(
                            PageUtils.getPageParam(json), nick, UserUtils.getUserName());
        }
        List<SysUser> sysUserList = page.getRecords();
        List<SysUser> result = new ArrayList<>();
        CollectionUtils.addAll(result, sysUserList.iterator());
        for (SysUser sysUser : sysUserList) {
            List<SysRole> sysRoleList = sysUser.getRoleList();
            boolean managerFlag = false;
            for (SysRole sysRole : sysRoleList) {
                if ("root".equals(sysRole.getRval()) || "operator".equals(sysRole.getRval())) {
                    managerFlag = true;
                    break;
                }
            }
            if (managerFlag) {
                result.remove(sysUser);
            }
        }
        page.setRecords(result);
        return Json.succ(oper).data("page", page);
    }

    @PermInfo("查询系统用户信息")
    //    @RequiresPermissions("a:sys:user:info")
    @GetMapping("/info")
    public Json userInfo() {
        String oper = "query user info";
        Object userInfo = SecurityUtils.getSubject().getPrincipal();
        return Json.succ(oper, "userInfo", userInfo);
    }

    @PermInfo("更新系统用户的信息")
    @RequiresPermissions("a:sys:user:info:update")
    @PatchMapping("/info")
    public Json update(@RequestBody String body) {
        String oper = "update user";
        log.info("{}, body: {}", oper, body);
        SysUser user = JSON.parseObject(body, SysUser.class);
        if (StringUtils.isNotBlank(user.getPwd())) {
            // 密码加密
            RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
            String salt = saltGen.nextBytes().toBase64();
            String hashedPwd = new Sha256Hash(user.getPwd(), salt, 1024).toBase64();
            user.setPwd(hashedPwd);
            user.setSalt(salt);
        } else {
            user.setPwd(null);
            user.setSalt(null);
        }
        user.setUname(null);
        user.setCreated(null);
        user.setUpdated(new Date());
        boolean success = sysUserService.updateById(user);
        return Json.result(oper, success).data("updated", user.getUpdated());
    }

    @PatchMapping("/pwd")
    public Json updatePwd(@RequestBody String body) {

        String oper = "update pwd";
        log.info("{}, body: {}", oper, body);

        JSONObject json = JSON.parseObject(body);
        String pwd = json.getString("pwd");

        if (StringUtils.isBlank(pwd)) {
            return Json.fail(oper, "无法更新密码：密码为空");
        }
        // 密码加密
        RandomNumberGenerator saltGen = new SecureRandomNumberGenerator();
        String salt = saltGen.nextBytes().toBase64();
        String hashedPwd = new Sha256Hash(pwd, salt, 1024).toBase64();
        SysUser currentUser = (SysUser) SecurityUtils.getSubject().getPrincipal();

        SysUser updateData = new SysUser();
        updateData.setUid(currentUser.getUid());
        updateData.setPwd(hashedPwd);
        updateData.setSalt(salt);
        updateData.setUpdated(new Date());
        boolean success = sysUserService.updateById(updateData);
        return Json.result(oper, success).data("updated", updateData.getUpdated());
    }

    @PermInfo("查找系统用户的角色")
    @RequiresPermissions("a:sys:user:role:find")
    @GetMapping("/{uid}/roles")
    public Json findUserRoles(@PathVariable String uid) {
        String oper = "find user roles";
        log.info("{}, uid: {}", oper, uid);
        if (StringUtils.isBlank(uid)) {
            return Json.fail(oper, "无法查询当前用户的角色值：参数为空（用户id）");
        }
        List<String> rids = sysRoleService.getRoleIdsByUserId(uid);
        return Json.succ(oper, "rids", rids);
    }
}
