package com.zwp.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zwp.usercenter.model.domain.Team;
import com.zwp.usercenter.model.domain.User;
import com.zwp.usercenter.model.dto.TeamQuery;
import com.zwp.usercenter.model.request.TeamJoinRequest;
import com.zwp.usercenter.model.request.TeamUpdateRequest;
import com.zwp.usercenter.model.vo.TeamUserVO;

import java.util.List;

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

    /**
     * 搜索队伍
     *
     * @param teamQuery
     * @param isAdmin
     * @return
     */
    List<TeamUserVO> listTeams(TeamQuery teamQuery, boolean isAdmin);

    /**
     * 修改队伍
     * @param teamUpdateRequest
     * @return
     */
    boolean updateTeam(TeamUpdateRequest teamUpdateRequest, User loginUser);

    /**
     * 加入队伍
     *
     * @param teamJoinRequest
     * @param loginUser
     * @return
     */
    Boolean joinTeam(TeamJoinRequest teamJoinRequest, User loginUser);
}
