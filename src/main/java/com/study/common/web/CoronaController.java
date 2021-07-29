package com.study.common.web;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.common.service.CoronaServiceimpl;
import com.study.common.vo.CoronaVO;

@Controller
@RequestMapping("/corona")
public class CoronaController {

    private CoronaServiceimpl coronaService;

    @GetMapping("/corona.wow")
    public String korea(Model model) throws IOException {

        List<CoronaVO> koreaStatsList = coronaService.getKoreaCovidDatas();
        model.addAttribute("koreaStats", koreaStatsList);

        return "corona/corona";

    }
}