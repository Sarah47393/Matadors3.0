<?php

namespace App\Controller;

use App\Entity\Reponse;
use App\Form\ReponseType;
use App\Repository\ReponseRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
/**
 * @Route("/reponse")
 */
class ReponseController extends AbstractController
{
    /**
     * @Route("/", name="reponse_index", methods={"GET"})
     */
    public function index(ReponseRepository $reponseRepository): Response
    {
        return $this->render('reponse/index.html.twig', [
            'reponses' => $reponseRepository->findAll(),
        ]);
    }


/**
     * @Route("/back", name="reponse_index_back", methods={"GET"})
     */
    public function index2(ReponseRepository $reponseRepository): Response
    {
        return $this->render('reponse-back/index.html.twig', [
            'reponses' => $reponseRepository->findAll(),
        ]);
    }


    /**
     * @Route("/new", name="reponse_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reponse = new Reponse();
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reponse);
            $entityManager->flush();

            return $this->redirectToRoute('reponse_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse/new.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }


/**
     * @Route("/back/new", name="reponse_new_back", methods={"GET", "POST"})
     */
    public function new2(Request $request, EntityManagerInterface $entityManager): Response
    {
        $reponse = new Reponse();
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($reponse);
            $entityManager->flush();

            return $this->redirectToRoute('reponse_index_back', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse-back/new.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }




    /**
     * @Route("/{id}", name="reponse_show", methods={"GET"})
     */
    public function show(Reponse $reponse): Response
    {
        return $this->render('reponse/show.html.twig', [
            'reponse' => $reponse,
        ]);
    }

    /**
     * @Route("/back/{id}", name="reponse_show_back", methods={"GET"}, requirements={"id":"\d+"})
    
     */
    public function show2(Reponse $reponse): Response
    {
        return $this->render('reponse-back/show.html.twig', [
            'reponse' => $reponse,
        ]);
    }
        /**
     * @Route("/back1/{id}", name="reponse_show2_back", methods={"GET"}, requirements={"id":"\d+"})
    
     */
    public function show12(Reponse $reponse): Response
    {
        return $this->render('reponse-back/show2.html.twig', [
            'reponse' => $reponse,
        ]);
    }



    /**
     * @Route("/{id}/edit", name="reponse_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Reponse $reponse, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('reponse_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse/edit.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/back/{id}/edit", name="reponse_edit_back", methods={"GET", "POST"})
     */
    public function edit2(Request $request, Reponse $reponse, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(ReponseType::class, $reponse);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('reponse_index_back', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reponse-back/edit.html.twig', [
            'reponse' => $reponse,
            'form' => $form->createView(),
        ]);
    }




    /**
     * @Route("/{id}", name="reponse_delete", methods={"POST"})
     */
    public function delete(Request $request, Reponse $reponse, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reponse->getId(), $request->request->get('_token'))) {
            $entityManager->remove($reponse);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reponse_index', [], Response::HTTP_SEE_OTHER);
    }




    /**
     * @Route("/back/{id}", name="reponse_delete_back", methods={"POST"})
     */
    public function delete2(Request $request, Reponse $reponse, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reponse->getId(), $request->request->get('_token'))) {
            $entityManager->remove($reponse);
            $entityManager->flush();
        }

        return $this->redirectToRoute('reponse_index_back', [], Response::HTTP_SEE_OTHER);
    }




}
