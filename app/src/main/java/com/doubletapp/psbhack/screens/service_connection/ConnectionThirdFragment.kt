package com.doubletapp.psbhack.screens.service_connection

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doubletapp.psbhack.R
import com.doubletapp.psbhack.extensions.setupActionBar
import kotlinx.android.synthetic.main.fragment_connection_third.*

class ConnectionThirdFragment : Fragment() {

    companion object {

        const val ARGS_ACTIVITIES = "args_activities"

        private const val LIST = "автомойка\n" +
                "автосервис\n" +
                "эвакуация и буксировка\n" +
                "администрирование\n" +
                "анализ данных\n" +
                "аниматор\n" +
                "аренда квартир\n" +
                "аренда машин\n" +
                "артист, музыкант, певец\n" +
                "благоустройство территории\n" +
                "бухгалтер, бухгалтерия\n" +
                "бытовой ремонт\n" +
                "бытовые услуги\n" +
                "вакцинация животных\n" +
                "вебмастер\n" +
                "ведение хозяйства\n" +
                "ведущий, шоумен, тамада\n" +
                "верстка и дизайн\n" +
                "видеооператор\n" +
                "водитель\n" +
                "гид, экскурсовод, грузчик\n" +
                "груминг\n" +
                "гувернантка\n" +
                "диетолог\n" +
                "дизайн\n" +
                "доставка\n" +
                "дрессировщик\n" +
                "животноводство\n" +
                "издательские услуги\n" +
                "исследования\n" +
                "кинология\n" +
                "компьютерный мастер\n" +
                "кондитер\n" +
                "консультирование\n" +
                "копирайтер\n" +
                "косметолог\n" +
                "кузнец\n" +
                "лес, охота, рыбалка\n" +
                "логопед\n" +
                "маникюр, педикюр\n" +
                "маркетинг, реклама\n" +
                "массажист\n" +
                "металлообработка\n" +
                "модель\n" +
                "модельер, дизайнер\n" +
                "носильщик\n" +
                "няня\n" +
                "обеспечение безопасности\n" +
                "обработка данных\n" +
                "обрядовые услуги\n" +
                "обслуживание\n" +
                "оператор\n" +
                "опросы, сбор мнений\n" +
                "отделка\n" +
                "оцифровка\n" +
                "парикмахер\n" +
                "переводчик\n" +
                "перевозка грузов\n" +
                "перевозка пассажиров\n" +
                "передержка животных\n" +
                "переработка отходов\n" +
                "писатель\n" +
                "платные туалеты\n" +
                "повар\n" +
                "полиграфия\n" +
                "посреднические услуги\n" +
                "пошив\n" +
                "предоставление лицензий\n" +
                "прием или сдача лома\n" +
                "программист\n" +
                "продукция собственного производства\n" +
                "проектирование\n" +
                "производственные услуги\n" +
                "прокат\n" +
                "психолог\n" +
                "ремонт бытовой техники\n" +
                "ремонт квартир\n" +
                "репетитор\n" +
                "реставрация\n" +
                "риэлтор\n" +
                "сантехник\n" +
                "сельхоз услуги\n" +
                "сиделка\n" +
                "социальная помощь\n" +
                "стилист\n" +
                "столяр, плотник\n" +
                "сторож\n" +
                "страховые услуги\n" +
                "строительство\n" +
                "тату и пирсинг\n" +
                "техническая поддержка\n" +
                "техобслуживание\n" +
                "ткани, кройка и шитьё\n" +
                "тренер, инстурктор\n" +
                "уборка и клининг\n" +
                "услуги по временному проживанию\n" +
                "услуги по сборке\n" +
                "услуги по хранению\n" +
                "уход за животными\n" +
                "учитель\n" +
                "финансовые услуги\n" +
                "химчистка\n" +
                "художник\n" +
                "электрик\n" +
                "эпиляция\n" +
                "юридические услуги"

        fun newInstance(activities: List<String>) = ConnectionThirdFragment().apply {
            arguments = Bundle().apply {
                putStringArrayList(ARGS_ACTIVITIES, ArrayList(activities))
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_connection_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActionBar(R.string.activity_variants, true, R.id.connection_third_toolbar)
        val selectedItems = requireArguments().getStringArrayList(ARGS_ACTIVITIES)!!
        val adapter = ConnectionThirdAdapter(ArrayList(selectedItems))
        connection_third_list.adapter = adapter
        adapter.submitItems(LIST.split("\n").map {
            val text = it.capitalize()
            ConnectionThirdAdapter.ConnectionThirdItem(text, selectedItems.contains(text))
        })
        connection_third_save.setOnClickListener {
            val intent = Intent().putExtra(ARGS_ACTIVITIES, adapter.selectedItems)
            targetFragment?.onActivityResult(targetRequestCode, Activity.RESULT_OK, intent)
            requireActivity().onBackPressed()
        }
    }
}