/* -*- Mode: Java; c-basic-offset: 4; tab-width: 4; indent-tabs-mode: nil; -*-
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */
package org.mozilla.rocket.privately.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.airbnb.lottie.LottieAnimationView
import org.mozilla.focus.R
import org.mozilla.focus.locale.LocaleAwareFragment
import org.mozilla.focus.utils.ThreadUtils
import org.mozilla.focus.widget.FragmentListener
import org.mozilla.focus.widget.FragmentListener.TYPE.SHOW_URL_INPUT
import org.mozilla.focus.widget.FragmentListener.TYPE.TOGGLE_PRIVATE_MODE


class PrivateHomeFragment : LocaleAwareFragment() {

    private lateinit var btnBack: RelativeLayout
    private lateinit var lottieMask: LottieAnimationView
    private lateinit var logoMan: LottieAnimationView
    private lateinit var fakeInput: View

    @Override
    override fun onCreate(bundle: Bundle?) {
        super.onCreate(bundle)
    }

    @Override
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, state: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_private_homescreen, container, false)
        btnBack = view.findViewById(R.id.pm_home_back)
        lottieMask = view.findViewById(R.id.pm_home_mask)
        logoMan = view.findViewById(R.id.pm_home_logo)
        fakeInput = view.findViewById(R.id.pm_home_fake_input)

        btnBack.setOnClickListener {
            var parent = activity
            if (parent is FragmentListener) {
                parent.onNotified(PrivateHomeFragment@ this, TOGGLE_PRIVATE_MODE, null)
            }
        }

        fakeInput.setOnClickListener {
            var parent = activity
            if (parent is FragmentListener) {
                parent.onNotified(PrivateHomeFragment@ this, SHOW_URL_INPUT, null)
            }
        }

        return view
    }

    @Override
    override fun onResume() {
        super.onResume()
        // Use onCreateAnimator or onCreateAnimation or NavController's addOnNavigatedListener to listen to fragment added complete events
        ThreadUtils.postToMainThreadDelayed({ animatePrivateHome() }, 500)


    }

    override fun applyLocale() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val FRAGMENT_TAG = "private_home_screen"

        fun create(): PrivateHomeFragment {
            return PrivateHomeFragment()
        }
    }

    private fun animatePrivateHome() {
        lottieMask.playAnimation()
        logoMan.playAnimation()
    }
}