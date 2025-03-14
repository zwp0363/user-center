package com.zwp.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwp.usercenter.model.domain.Team;
import com.zwp.usercenter.model.domain.User;

/**
* @author zwp
* @description 针对表【team(队伍)】的数据库操作Service
* @createDate 2025-03-14 14:49:20
*/
public interface TeamService extends IService<Team> {

    /**
     * 创建队伍
     * @param team
     * @return
     */
    long addTeam(Team team, User loginUser);
}
